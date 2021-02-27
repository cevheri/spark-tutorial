package org.cevher.spark.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;

public class IncomingJava implements Serializable {
    private String applicationName;
    private String applicationVersion;
    private String applicationInstanceName;
    private String applicationInstanceIp = null;
    private String startTime;
    private String endTime;
    private String url;
    private String requestType;
    private String className;
    private String methodName;
    private String userLoginName;
    private String userSessionId;
    private String exitCode;
    private String exceptionType;
    private String exceptionMessage;
    private String exceptionStackTrace;
    private String externalData;

    @JsonIgnore
    private ArrayList<IncomingParamJava> inParams = new ArrayList<>();
    @JsonIgnore
    private ArrayList<IncomingParamJava> outParams = new ArrayList<>();

    public ArrayList<IncomingParamJava> getInParams() {
        return inParams;
    }

    public void setInParams(ArrayList<IncomingParamJava> inParams) {
        this.inParams = inParams;
    }

    public ArrayList<IncomingParamJava> getOutParams() {
        return outParams;
    }

    public void setOutParams(ArrayList<IncomingParamJava> outParams) {
        this.outParams = outParams;
    }
// Getter Methods

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public String getApplicationInstanceName() {
        return applicationInstanceName;
    }

    public String getApplicationInstanceIp() {
        return applicationInstanceIp;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public String getExitCode() {
        return exitCode;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public String getExternalData() {
        return externalData;
    }

    // Setter Methods

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public void setApplicationInstanceName(String applicationInstanceName) {
        this.applicationInstanceName = applicationInstanceName;
    }

    public void setApplicationInstanceIp(String applicationInstanceIp) {
        this.applicationInstanceIp = applicationInstanceIp;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void setExceptionStackTrace(String exceptionStackTrace) {
        this.exceptionStackTrace = exceptionStackTrace;
    }

    public void setExternalData(String externalData) {
        this.externalData = externalData;
    }


}