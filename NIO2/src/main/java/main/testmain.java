package main;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class testmain {

    public static void main( String[] args) throws Exception {
//        TestDirectoryStream();
        TestwalkFileTree();
    }
    public static void TestPath(){
        Path listing = Paths.get("/ usr/ bin/ zip");// 创建 绝对 路径
        System. out. println(" File Name [" + listing. getFileName() + "]");//获取 文件名
        /*① 获取 名称 元素 的 数量*/
        System. out. println(" Number of Name Elements in the Path [" + listing. getNameCount() + "]");
        /*② 获取 Path 的 信息*/ System. out. println(" Parent Path [" + listing. getParent() + "]");
        System. out. println(" Root of Path [" + listing. getRoot() + "]");
        System. out. println(" Subpath from Root, 2 elements deep [" + listing. subpath( 0, 2) + "]");
    }

    /**
     * 找出当前目录下的。无法下钻
     */
    public static void TestDirectoryStream(){
        Path dir = Paths. get("E:\\WorkSpace\\GIT");// ① 设定 起始 路径
        try( DirectoryStream< Path> stream = Files.newDirectoryStream( dir, "*.properties")){
            // ② 声明 过滤 流 //③ 找出 所有. properties 文件 并 输出
            for (Path entry: stream) {
                System. out. println( entry.getFileName());
            }
        } catch (IOException e) { System. out. println( e. getMessage());
        }
    }

    public static void TestwalkFileTree() throws IOException {

        Path startingDir = Paths. get("E:\\WorkSpace\\GIT\\edc-plsqltool\\edc-plsqltool-ms\\src\\main\\java");
        // 设置 起始 目录
        Files. walkFileTree( startingDir, new FindJavaVisitor());
        //① 调用 walkFileTree
    }
    private static class FindJavaVisitor extends SimpleFileVisitor< Path>
    //② 扩展 SimpleFileVisitor< Path>
    {
        @Override
        public FileVisitResult visitFile( Path file, BasicFileAttributes attrs)
        //③ 重写 visitFile 方法
        {
            if (file.toString().endsWith(".java"))
            {
                System. out. println( file. getFileName());
            }
            return FileVisitResult. CONTINUE;
        }
    }

}
