package com.mesozi.notificationservice.controllers;

import java.util.Map;

import com.mesozi.notificationservice.hashmap.NotificationsMap;
import com.mesozi.notificationservice.models.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/noti")
public class NoticeController {

    @Autowired
    private NotificationsMap notificationsMap;

    @GetMapping(path = "/notice")
    public Map<String, String> getNotice(@RequestParam String name) {

        return notificationsMap.getKeyMap(name);

    }

    @GetMapping(path = "/noticeAll")
    public Map<String, String> getAllNotice() {

        return notificationsMap.getFullMap();

    }

    @PostMapping(path = "/notice")
    public Map<String, String> createNotice(@RequestBody Notice notice) {

        return notificationsMap.populateMap(notice.getNoticeName(), notice.getNoticeMessage());

    }

}