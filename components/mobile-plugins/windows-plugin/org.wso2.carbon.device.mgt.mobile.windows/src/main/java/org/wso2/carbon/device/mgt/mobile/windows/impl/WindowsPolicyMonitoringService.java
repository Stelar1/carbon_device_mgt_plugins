/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.device.mgt.mobile.windows.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.device.mgt.common.Device;
import org.wso2.carbon.device.mgt.common.DeviceIdentifier;
import org.wso2.carbon.device.mgt.common.DeviceManagementConstants;
import org.wso2.carbon.policy.mgt.common.Policy;
import org.wso2.carbon.policy.mgt.common.monitor.ComplianceData;
import org.wso2.carbon.policy.mgt.common.monitor.ComplianceFeature;
import org.wso2.carbon.policy.mgt.common.monitor.PolicyComplianceException;
import org.wso2.carbon.policy.mgt.common.spi.PolicyMonitoringService;

import java.util.ArrayList;
import java.util.List;

public class WindowsPolicyMonitoringService implements PolicyMonitoringService {

    private static Log log = LogFactory.getLog(WindowsPolicyMonitoringService.class);

    @Override
    public void notifyDevices(List<Device> list) throws PolicyComplianceException {

    }

    @Override
    public ComplianceData checkPolicyCompliance(DeviceIdentifier deviceIdentifier, Policy policy, Object compliancePayload)
            throws PolicyComplianceException {
        if (log.isDebugEnabled()) {
            log.debug("checking policy compliance status of device '" + deviceIdentifier.getId() + "'");
        }
        List<ComplianceFeature> complianceFeatures = (List<ComplianceFeature>) compliancePayload;
        List<ComplianceFeature> nonComplianceFeatures = new ArrayList<>();
        ComplianceData complianceData = new ComplianceData();

        if (policy == null || compliancePayload == null) {
            return complianceData;
        }

        for (ComplianceFeature complianceFeature : complianceFeatures) {
            if (!complianceFeature.isCompliant()) {
                complianceData.setStatus(false);
                nonComplianceFeatures.add(complianceFeature);
                break;
            }
        }
        complianceData.setComplianceFeatures(nonComplianceFeatures);
        return complianceData;
    }

    @Override
    public String getType() {
        return DeviceManagementConstants.MobileDeviceTypes.MOBILE_DEVICE_TYPE_WINDOWS;
    }
}
