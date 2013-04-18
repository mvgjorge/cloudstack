# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
from marvin.integration.lib.base import CloudStackEntity
from marvin.cloudstackAPI import createRemoteAccessVpn
from marvin.cloudstackAPI import listRemoteAccessVpns
from marvin.cloudstackAPI import deleteRemoteAccessVpn

class RemoteAccessVpn(CloudStackEntity.CloudStackEntity):


    def __init__(self, items):
        self.__dict__.update(items)


    @classmethod
    def create(cls, apiclient, factory, **kwargs):
        cmd = createRemoteAccessVpn.createRemoteAccessVpnCmd()
        [setattr(cmd, factoryKey, factoryValue) for factoryKey, factoryValue in factory.__dict__.iteritems()]
        [setattr(cmd, key, value) for key,value in kwargs.iteritems()]
        remoteaccessvpn = apiclient.createRemoteAccessVpn(cmd)
        return RemoteAccessVpn(remoteaccessvpn.__dict__)


    @classmethod
    def list(self, apiclient, publicipid, **kwargs):
        cmd = listRemoteAccessVpns.listRemoteAccessVpnsCmd()
        cmd.publicipid = publicipid
        [setattr(cmd, key, value) for key,value in kwargs.iteritems()]
        remoteaccessvpn = apiclient.listRemoteAccessVpns(cmd)
        return map(lambda e: RemoteAccessVpn(e.__dict__), remoteaccessvpn)


    def delete(self, apiclient, publicipid, **kwargs):
        cmd = deleteRemoteAccessVpn.deleteRemoteAccessVpnCmd()
        cmd.id = self.id
        cmd.publicipid = publicipid
        [setattr(cmd, key, value) for key,value in kwargs.iteritems()]
        remoteaccessvpn = apiclient.deleteRemoteAccessVpn(cmd)
        return remoteaccessvpn
