package com.timsmeet.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

@Controller
@RequestMapping(value = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
public class VersionController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public VersionInfo gerVersion(@RequestParam(required = false) String name) {

        VersionInfo info = new VersionInfo();
        info.setVersion("0.1.0");
        info.setName(Strings.isNullOrEmpty(name) ? "unknown" : name);
        
        return info;
    }
    
    public static class VersionInfo {
        private String version;
        private String name;
        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
