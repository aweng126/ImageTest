package com.example.kingwen.imagetest.Beans;

public class MyItem1 extends Myitem{
   private String StrImg;
   public MyItem1(String strImg) {
       StrImg = strImg;
   }

   public String getUri() {

       return StrImg;
   }

   public void setStrImg(String strImg) {
       StrImg = strImg;
   }
}
