package service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThtradManage {
    public static void main(String[] args) {
        ThreadMXBean MX = ManagementFactory.getThreadMXBean();

        ThreadInfo[] infos = MX.dumpAllThreads(false,false);
        for (ThreadInfo info : infos) {
            System.out.println(info.getThreadState());
        }
    }
}
