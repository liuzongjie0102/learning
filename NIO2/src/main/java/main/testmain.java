package main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class testmain {
    public static void main(String[] args){
        Path listing = Paths.get("D:\\GitHub\\lzj_project\\NIO2");

        System. out. println(" File Name [" + listing. getFileName() + "]");//获取 文件名
        /*① 获取 名称 元素 的 数量*/
        System. out. println(" Number of Name Elements in the Path [" + listing. getNameCount() + "]");
        /*② 获取 Path 的 信息*/ System. out. println(" Parent Path [" + listing. getParent() + "]");
        System. out. println(" Root of Path [" + listing. getRoot() + "]");
        System. out. println(" Subpath from Root, 2 elements deep [" + listing. subpath( 0, 2) + "]");

    }
}
