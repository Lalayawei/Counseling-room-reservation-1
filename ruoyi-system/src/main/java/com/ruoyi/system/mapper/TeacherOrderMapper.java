package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.CRoom;
import com.ruoyi.system.domain.TeacherOrder;

/**
 * 辅导室预约信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-22
 */
public interface TeacherOrderMapper
{
    /**
     * 查询辅导室预约信息
     *
     * @param timeId 辅导室预约信息主键
     * @return 辅导室预约信息
     */
    public TeacherOrder selectTeacherOrderByTimeId(Long timeId);

    /**
     * 查询辅导室预约信息列表
     *
     * @param teacherOrder 辅导室预约信息
     * @return 辅导室预约信息集合
     */
    public List<TeacherOrder> selectTeacherOrderList(TeacherOrder teacherOrder);

    /**
     * 新增辅导室预约信息
     *
     * @param teacherOrder 辅导室预约信息
     * @return 结果
     */
    public int insertTeacherOrder(TeacherOrder teacherOrder);

    /**
     * 修改辅导室预约信息
     *
     * @param teacherOrder 辅导室预约信息
     * @return 结果
     */
    public int updateTeacherOrder(TeacherOrder teacherOrder);

    /**
     * 删除辅导室预约信息
     *
     * @param timeId 辅导室预约信息主键
     * @return 结果
     */
    public int deleteTeacherOrderByTimeId(Long timeId);

    /**
     * 批量删除辅导室预约信息
     *
     * @param timeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeacherOrderByTimeIds(Long[] timeIds);

}
