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
		if (file != null) {// �ж��ϴ����ļ��Ƿ�Ϊ��
			String path = null;// �ļ�·��
			String type = null;// �ļ�����
			String fileName = file.getOriginalFilename();// �ļ�ԭ����
			System.out.println("�ϴ����ļ�ԭ����:" + fileName);
			// �ж��ļ�����
			type = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// �ж��ļ������Ƿ�Ϊ��
				if ("GIF".equals(type.toUpperCase())
						|| "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// ��Ŀ��������ʵ�ʷ������еĸ�·��
					String realPath = request.getSession().getServletContext()
							.getRealPath("/");
					System.out.println(realPath);
					// �Զ�����ļ�����
					String trueFileName = String.valueOf(System
							.currentTimeMillis()) + fileName;
					System.out.println(trueFileName);
					// ���ô��ͼƬ�ļ���·��
					path = realPath +"sphoto\\" + trueFileName;
					System.out.println("���ͼƬ�ļ���·��:" + path);
					// ת���ļ���ָ����·��
					try {
						file.transferTo(new File(path));
					} catch (Exception e) {

						e.printStackTrace();
					}
					System.out.println("�ļ��ɹ��ϴ���ָ��Ŀ¼��");
					Student student = new Student();
					student.setSid((String)session.getAttribute("zhuceid"));
					student.setUrl(trueFileName);
					b = photoService.updatePhoto(student);
				} else {
					System.out.println("����������Ҫ���ļ�����,�밴Ҫ�������ϴ�");
					return null;
				}
			} else {
				System.out.println("�ļ�����Ϊ��");
				return null;
			}
		} else {
			System.out.println("û���ҵ����Ӧ���ļ�");
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
