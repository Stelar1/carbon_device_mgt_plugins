/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.device.mgt.iot.androidsense.service.impl;

import org.wso2.carbon.apimgt.annotations.api.API;
import org.wso2.carbon.apimgt.annotations.api.Permission;
import org.wso2.carbon.device.mgt.extensions.feature.mgt.annotations.DeviceType;
import org.wso2.carbon.device.mgt.extensions.feature.mgt.annotations.Feature;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@DeviceType(value = "android_sense")
@API(name = "android_sense", version = "1.0.0", context = "/android_sense", tags = {"android_sense"})
public interface AndroidSenseService {

    /**
     * End point to send the key words to the device
     *
     * @param deviceId The registered device Id.
     * @param keywords The key words to be sent. (Comma separated values)
     */
    @Path("device/{deviceId}/words")
    @POST
    @Feature(code = "keywords", name = "Add Keywords", description = "Send keywords to the device")
    @Permission(scope = "android_sense_user", permissions = {"/permission/admin/device-mgt/user/operations"})
    Response sendKeyWords(@PathParam("deviceId") String deviceId, @QueryParam("keywords") String keywords);

    /**
     * End point to send the key words to the device
     *
     * @param deviceId  The registered device Id.
     * @param threshold The key words to be sent. (Comma separated values)
     */
    @Path("device/{deviceId}/words/threshold")
    @POST
    @Feature(code = "threshold", name = "Add a Threshold", description = "Set a threshold for word in the device")
    @Permission(scope = "android_sense_user", permissions = {"/permission/admin/device-mgt/user/operations"})
    Response sendThreshold(@PathParam("deviceId") String deviceId, @QueryParam("threshold") String threshold);

    @Path("device/{deviceId}/words")
    @DELETE
    @Feature(code = "remove", name = "Remove Keywords", description = "Remove the keywords")
    @Permission(scope = "android_sense_user", permissions = {"/permission/admin/device-mgt/user/operations"})
    Response removeKeyWords(@PathParam("deviceId") String deviceId, @QueryParam("words") String words);

    /**
     * Retrieve Sensor data for the device type
     */
    @Path("stats/{deviceId}/sensors/{sensorName}")
    @GET
    @Consumes("application/json")
    @Permission(scope = "android_sense_user", permissions = {"/permission/admin/device-mgt/user/stats"})
    @Produces("application/json")
    Response getAndroidSenseDeviceStats(@PathParam("deviceId") String deviceId, @PathParam("sensorName") String sensor,
                                        @QueryParam("from") long from, @QueryParam("to") long to);

    /**
     * Enroll devices.
     */
    @Path("device/{device_id}/register")
    @POST
    @Permission(scope = "android_sense_user", permissions = {"/permission/admin/device-mgt/user/devices"})
    Response register(@PathParam("device_id") String deviceId, @QueryParam("deviceName") String deviceName);

}

