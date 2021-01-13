package com.example.demo.controller;

import com.example.demo.oplog.OpLog;
import com.newland.bd.ms.core.model.RespInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class DemoController {

    @OpLog(opType = OpLog.OpType.ADD, opItem = "test", opItemIdExpression = "#respInfo.respResult")
    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testController(@RequestBody RespInfo respInfo){
        return "test";
    }
}
