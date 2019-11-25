package com.glsx.vasp.admin.modules;

import com.glsx.vasp.admin.common.config.FastDFSClientWrapper;
import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.controller.BaseController;
import com.glsx.vasp.web.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller公共组件,每个Controller都应该继承
 */
@RestController
public class CommonController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FastDFSClientWrapper dfsClient;

    protected SysUser getUser() {
        return ShiroUtils.currentUser();
    }

    protected Integer getUserId() {
        return getUser().getAccountId();
    }

    protected Integer getOrgId() {
        SysUser user = getUser();
        return user.getOrgId();
    }

    @SysLog("上传文件")
    @PostMapping(value = "common/file/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws IOException {
        String path = dfsClient.uploadFile(file);
        logger.info("上传文件url:" + path);
        return R.ok().data(path);
    }

    @SysLog("删除上传文件")
    @PostMapping(value = "common/file/delete")
    public R upload(@RequestParam("fileUrl") String fileUrl) throws IOException {
        dfsClient.deleteFile(fileUrl);
        //todo删除图片数据
        return R.ok();
    }

//    @RequestMapping("common/download")
//    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
//        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
//        try {
//            String filePath = Global.getDownloadPath() + fileName;
//
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("multipart/form-data");
//            response.setHeader("Content-Disposition", "attachment;fileName=" + setFileDownloadHeader(request, realFileName));
//            FileUtils.writeBytes(filePath, response.getOutputStream());
//            if (delete) {
//                FileUtils.deleteFile(filePath);
//            }
//        } catch (Exception e) {
//            logger.error("下载文件失败", e);
//        }
//    }

}
