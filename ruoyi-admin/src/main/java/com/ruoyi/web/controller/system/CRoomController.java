package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.CRoom;
import com.ruoyi.system.service.ICRoomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 辅导室Controller
 *
 * @author ruoyi
 * @date 2022-04-21
 */
@RestController
@RequestMapping("/system/room")
public class CRoomController extends BaseController
{
    @Autowired
    private ICRoomService cRoomService;

    /**
     * 查询辅导室列表
     */
    @PreAuthorize("@ss.hasPermi('system:room:list')")
    @GetMapping("/list")
    public TableDataInfo list(CRoom cRoom)
    {
        startPage();
        List<CRoom> list = cRoomService.selectCRoomList(cRoom);
        return getDataTable(list);
    }

    /**
     * 导出辅导室列表
     */
    @PreAuthorize("@ss.hasPermi('system:room:export')")
    @Log(title = "辅导室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CRoom cRoom)
    {
        List<CRoom> list = cRoomService.selectCRoomList(cRoom);
        ExcelUtil<CRoom> util = new ExcelUtil<CRoom>(CRoom.class);
        util.exportExcel(response, list, "辅导室数据");
    }

    /**
     * 获取辅导室详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:room:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return AjaxResult.success(cRoomService.selectCRoomByRoomId(roomId));
    }

    /**
     * 新增辅导室
     */
    @PreAuthorize("@ss.hasPermi('system:room:add')")
    @Log(title = "辅导室", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CRoom cRoom)
    {
        return toAjax(cRoomService.insertCRoom(cRoom));
    }

    /**
     * 修改辅导室
     */
    @PreAuthorize("@ss.hasPermi('system:room:edit')")
    @Log(title = "辅导室", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CRoom cRoom)
    {
        return toAjax(cRoomService.updateCRoom(cRoom));
    }

    /**
     * 删除辅导室
     */
    @PreAuthorize("@ss.hasPermi('system:room:remove')")
    @Log(title = "辅导室", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(cRoomService.deleteCRoomByRoomIds(roomIds));
    }
}
