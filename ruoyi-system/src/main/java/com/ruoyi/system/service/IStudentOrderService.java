package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.StudentOrder;

/**
 * 解疑辅导室预约信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-25
 */
public interface IStudentOrderService 
{
    /**
     * 查询解疑辅导室预约信息
     * 
     * @param timeId 解疑辅导室预约信息主键
     * @return 解疑辅导室预约信息
     */
    public StudentOrder selectStudentOrderByTimeId(Long timeId);

    /**
     * 查询解疑辅导室预约信息列表
     * 
     * @param studentOrder 解疑辅导室预约信息
     * @return 解疑辅导室预约信息集合
     */
    public List<StudentOrder> selectStudentOrderList(StudentOrder studentOrder);

    /**
     * 新增解疑辅导室预约信息
     * 
     * @param studentOrder 解疑辅导室预约信息
     * @return 结果
     */
    public int insertStudentOrder(StudentOrder studentOrder);

    /**
     * 修改解疑辅导室预约信息
     * 
     * @param studentOrder 解疑辅导室预约信息
     * @return 结果
     */
    public int updateStudentOrder(StudentOrder studentOrder);

    /**
     * 批量删除解疑辅导室预约信息
     * 
     * @param timeIds 需要删除的解疑辅导室预约信息主键集合
     * @return 结果
     */
    public int deleteStudentOrderByTimeIds(Long[] timeIds);

    /**
     * 删除解疑辅导室预约信息信息
     * 
     * @param timeId 解疑辅导室预约信息主键
     * @return 结果
     */
    public int deleteStudentOrderByTimeId(Long timeId);
}
