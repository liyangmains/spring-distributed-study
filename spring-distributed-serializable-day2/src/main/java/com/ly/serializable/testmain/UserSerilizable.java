package com.ly.serializable.testmain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ly.serializable.user.User;

public class UserSerilizable {
	
	public static void main(String[] args) {
		//序列化
		String info = getSerilizable();
		System.out.println(info);
		//反序列化
		User user = getDeSerilizable();
		System.out.println(user);
	}

	public static String getSerilizable(){
		ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(new File("userSerilizable")));
				User user = new User();
				user.setUserName("张飞");
				user.setAge(18);
				oos.writeObject(user);
				return "序列化成功";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(oos != null){
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		return null;
	}
	public static User getDeSerilizable(){
		ObjectInputStream ois = null;
		User user = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("userSerilizable")));
			try {
				user = (User)ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return user;
	}
}
