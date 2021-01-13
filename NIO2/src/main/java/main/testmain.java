package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class testmain {

    public static void main( String[] args) throws Exception {
//        TestDirectoryStream();
//        TestwalkFileTree();
//        fastRead();
        changeUnio();
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

    /**
     * 列出目录下所有文件
     * @throws IOException
     */
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

    /**
     * 快速 读写 数据
     */
    private static void fastRead() throws IOException {
        Path logFile = Paths.get("C:\\Users\\43853\\Desktop\\新建文本文档.sh");
        List< String> lines = Files.readAllLines( logFile, StandardCharsets.UTF_8);
        try (BufferedWriter writer = Files.newBufferedWriter( logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND))
        {
            writer. write("Hello World!");
        }
        byte[] bytes = Files.readAllBytes( logFile);
        String out = new String(bytes);
        System.out.println(out);
    }

    private static void changeUnio() throws Exception {

        File fileIn = new File("C:\\Users\\43853\\Desktop\\b67c34fcc23643dcaf0699f2326d3952.csv");

        try (InputStream is = new FileInputStream(fileIn);
                        InputStreamReader isr = new InputStreamReader(is,"gbk");
                        BufferedReader buff = new BufferedReader(isr);
        ){
            String str = null;
            while ( (str = buff.readLine()) != null ){
                System.out.println(str);
//                System.out.println(new String(str.getBytes("GBK"), "UTF-8"));
            }
        }
    }

}
