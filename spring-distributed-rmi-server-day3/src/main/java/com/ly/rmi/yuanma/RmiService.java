package com.ly.rmi.yuanma;

public class RmiService {

	public static void main(String[] args) {
		Persion persion = new Persion();
		persion.setUserName("张飞");
		Persion_Skeleton skeletion = new Persion_Skeleton(persion);
		skeletion.start();
	}
}
