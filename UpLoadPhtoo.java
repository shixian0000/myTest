package com.oracle.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Student;
import com.oracle.ssm.service.PhotoService;

@Controller
public class UpLoadPhtoo {
	@Autowired
	private PhotoService photoService;
	
	@ResponseBody
	
	
	@RequestMapping("/photoUpload")
	public ModelAndView photoUpload(MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("===========");
		boolean b = false;
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase())
						|| "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
					String realPath = request.getSession().getServletContext()
							.getRealPath("/");
					System.out.println(realPath);
					// 自定义的文件名称
					String trueFileName = String.valueOf(System
							.currentTimeMillis()) + fileName;
					System.out.println(trueFileName);
					// 设置存放图片文件的路径
					path = realPath +"sphoto\\" + trueFileName;
					System.out.println("存放图片文件的路径:" + path);
					// 转存文件到指定的路径
					try {
						file.transferTo(new File(path));
					} catch (Exception e) {

						e.printStackTrace();
					}
					System.out.println("文件成功上传到指定目录下");
					Student student = new Student();
					student.setSid((String)session.getAttribute("zhuceid"));
					student.setUrl(trueFileName);
					b = photoService.updatePhoto(student);
				} else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			} else {
				System.out.println("文件类型为空");
				return null;
			}
		} else {
			System.out.println("没有找到相对应的文件");
			return null;
		}
		ModelAndView mav = new ModelAndView();
		if(b){
			mav.setViewName("login");
		}else{
			mav.setViewName("uploatphoto");
		}
		
		return mav;

	}
}
