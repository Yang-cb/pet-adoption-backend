package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.constant.MessageConstant;
import com.ycb.common.constant.ReportTypeConstant;
import com.ycb.common.constant.StatusConstant;
import com.ycb.common.result.PageResult;
import com.ycb.common.utils.EmailUtils;
import com.ycb.exception.EmailException;
import com.ycb.mapper.AccountMapper;
import com.ycb.mapper.AdminMapper;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.pojo.dto.*;
import com.ycb.pojo.vo.AllReportVO;
import com.ycb.pojo.vo.AllWantAdoptVO;
import com.ycb.pojo.vo.AllAccountVO;
import com.ycb.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private PetBulletinMapper petBulletinMapper;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private EmailUtils emailUtils;
    @Resource
    private AccountMapper accountMapper;

    @Override
    public PageResult getAllAccount(PageAccountDTO dto) {
        // 分页查询
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllAccountVO> page = adminMapper.getAllAccount(dto);
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Override
    public PageResult getAllWantAdopt(PageWantAdoptDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllWantAdoptVO> page = adminMapper.getAllWantAdopt(dto);
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Override
    public PageResult getAllReport(PageReportDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllReportVO> page = adminMapper.getAllReport(dto);
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Override
    public void updateBulletinStatus(UpdateBulletinStatusDTO dto) {
        int bulletinId = petBulletinMapper.getBulletinIdByPetId(dto.getPetId());
        dto.setBulletinId(bulletinId);
        adminMapper.updateBulletinStatus(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReportStatus(UpdateReportStatusDTO dto) {
        // 如果操作的是用户，需要发送邮件
        if (dto.getDisableType() == ReportTypeConstant.REPORT_TYPE_ACCOUNT) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            String time = simpleDateFormat.format(date);
            // 发送邮件
            String email = accountMapper.getEmailById(dto.getDisableId());
            SimpleMailMessage mailMessage = switch (dto.getToStatus()) {
                case StatusConstant.DISABLE ->
                        emailUtils.createMailMessage("您的账户已被禁用", "经审查，您的账户可能包含［ " + dto.getDisableCheck() + " ], \n"
                                + dto.getDisableText() + "\n" + "此账户在" + time + "被停用。", email);
                case StatusConstant.ENABLE ->
                        emailUtils.createMailMessage("您的账户已解封", "您的账户将于" + time + "解除封禁。", email);
                default -> throw new IllegalStateException(MessageConstant.NOT_EXIST_TYPE);
            };
            try {
                javaMailSender.send(mailMessage);
            } catch (MailException e) {
                throw new EmailException(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.EMAIL_SEND_FAILED);
            } finally {
                adminMapper.updateAccountStatus(dto);
            }
            // 如果是禁用操作，需要添加到禁用表
            if (dto.getToStatus() == StatusConstant.DISABLE)
                adminMapper.addDisable(dto);
        } else if (dto.getDisableType() == ReportTypeConstant.REPORT_TYPE_PET && dto.getToStatus() == StatusConstant.REVIEW_PASS) {
            // 如果是禁用宠物，需要删除宠物，并添加到禁用表
            int bulletinId = petBulletinMapper.getBulletinIdByPetId(dto.getDisableId());
            DeletePetBulletinDTO deletePetBulletinDTO =
                    DeletePetBulletinDTO.builder().petId(dto.getDisableId()).bulletinId(bulletinId).build();
            petBulletinMapper.deletePetByPetId(deletePetBulletinDTO);
            petBulletinMapper.deleteBulletinByBulletinId(deletePetBulletinDTO);
            adminMapper.addDisable(dto);
        }
        // 如果是举报界面的请求，需要修改举报状态
        if (dto.getReportId() != null)
            adminMapper.updateReportStatus(dto);
    }
}
