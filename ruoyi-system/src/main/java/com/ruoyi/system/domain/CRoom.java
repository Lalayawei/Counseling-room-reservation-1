package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 辅导室对象 c_room
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
public class CRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 辅导室编号 */
    @Excel(name = "辅导室编号")
    private Long roomId;

    /** 辅导室简介 */
    @Excel(name = "辅导室简介")
    private String introduction;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 可容纳人数 */
    @Excel(name = "可容纳人数")
    private Long userNum;

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setUserNum(Long userNum) 
    {
        this.userNum = userNum;
    }

    public Long getUserNum() 
    {
        return userNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("introduction", getIntroduction())
            .append("location", getLocation())
            .append("userNum", getUserNum())
            .toString();
    }
}
