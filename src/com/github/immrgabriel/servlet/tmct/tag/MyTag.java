//package com.github.immrgabriel.servlet.tmct.tag;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.tagext.TagSupport;
//import java.io.IOException;
//
//public class MyTag extends TagSupport{
//    private String name;
//
//    @Override
//    public int doStartTag() throws JspException {
//        try {
//            if (name == null) {
//                pageContext.getOut().write("Hello, world!");
//            } else {
//                pageContext.getOut().write("Hello, world! I'm " + name);
//            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return SKIP_BODY;
//    }
//
//    @Override
//    public void release() {
//        super.release();
//        name = null;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
