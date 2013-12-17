// 
// Copyright (c) Microsoft and contributors.  All rights reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// 
// See the License for the specific language governing permissions and
// limitations under the License.
// 

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.servicebus;

import com.microsoft.windowsazure.management.servicebus.models.AccessRight;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusConnectionDetail;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusConnectionDetailsResponse;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusNotificationHub;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusNotificationHubResponse;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusNotificationHubsResponse;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusSharedAccessAuthorizationRule;
import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.core.ServiceOperations;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
* The Service Bus Management API includes operations for managing Service Bus
* queues.
*/
public class NotificationHubOperationsImpl implements ServiceOperations<ServiceBusManagementClientImpl>, NotificationHubOperations
{
    /**
    * Initializes a new instance of the NotificationHubOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    NotificationHubOperationsImpl(ServiceBusManagementClientImpl client)
    {
        this.client = client;
    }
    
    private ServiceBusManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.servicebus.ServiceBusManagementClientImpl.
    */
    public ServiceBusManagementClientImpl getClient() { return this.client; }
    
    /**
    * Lists the notification hubs associated with a namespace.
    *
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<ServiceBusNotificationHubResponse> getAsync(final String namespaceName, final String notificationHubName)
    {
        return this.getClient().getExecutorService().submit(new Callable<ServiceBusNotificationHubResponse>() { 
            @Override
            public ServiceBusNotificationHubResponse call() throws Exception
            {
                return get(namespaceName, notificationHubName);
            }
         });
    }
    
    /**
    * Lists the notification hubs associated with a namespace.
    *
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public ServiceBusNotificationHubResponse get(String namespaceName, String notificationHubName) throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException, URISyntaxException
    {
        // Validate
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/servicebus/namespaces/" + namespaceName + "/NotificationHubs/" + notificationHubName;
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-08-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        ServiceBusNotificationHubResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServiceBusNotificationHubResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry");
        Element entryElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (entryElement != null)
        {
            NodeList elements2 = entryElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "title");
            Element titleElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
            if (titleElement != null)
            {
            }
            
            NodeList elements3 = entryElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "content");
            Element contentElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
            if (contentElement != null)
            {
                NodeList elements4 = contentElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "NotificationHubDescription");
                Element notificationHubDescriptionElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                if (notificationHubDescriptionElement != null)
                {
                    ServiceBusNotificationHub notificationHubDescriptionInstance = new ServiceBusNotificationHub();
                    result.setNotificationHub(notificationHubDescriptionInstance);
                    
                    NodeList elements5 = notificationHubDescriptionElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "RegistrationTtl");
                    Element registrationTtlElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                    if (registrationTtlElement != null)
                    {
                        String registrationTtlInstance;
                        registrationTtlInstance = registrationTtlElement.getTextContent();
                        notificationHubDescriptionInstance.setRegistrationTtl(registrationTtlInstance);
                    }
                    
                    NodeList elements6 = notificationHubDescriptionElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationRules");
                    Element authorizationRulesSequenceElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
                    if (authorizationRulesSequenceElement != null)
                    {
                        for (int i1 = 0; i1 < authorizationRulesSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationRule").getLength(); i1 = i1 + 1)
                        {
                            org.w3c.dom.Element authorizationRulesElement = ((org.w3c.dom.Element)authorizationRulesSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationRule").item(i1));
                            ServiceBusSharedAccessAuthorizationRule authorizationRuleInstance = new ServiceBusSharedAccessAuthorizationRule();
                            notificationHubDescriptionInstance.getAuthorizationRules().add(authorizationRuleInstance);
                            
                            NodeList elements7 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ClaimType");
                            Element claimTypeElement = elements7.getLength() > 0 ? ((Element)elements7.item(0)) : null;
                            if (claimTypeElement != null)
                            {
                                String claimTypeInstance;
                                claimTypeInstance = claimTypeElement.getTextContent();
                                authorizationRuleInstance.setClaimType(claimTypeInstance);
                            }
                            
                            NodeList elements8 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ClaimValue");
                            Element claimValueElement = elements8.getLength() > 0 ? ((Element)elements8.item(0)) : null;
                            if (claimValueElement != null)
                            {
                                String claimValueInstance;
                                claimValueInstance = claimValueElement.getTextContent();
                                authorizationRuleInstance.setClaimValue(claimValueInstance);
                            }
                            
                            NodeList elements9 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "Rights");
                            Element rightsSequenceElement = elements9.getLength() > 0 ? ((Element)elements9.item(0)) : null;
                            if (rightsSequenceElement != null)
                            {
                                for (int i2 = 0; i2 < rightsSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AccessRights").getLength(); i2 = i2 + 1)
                                {
                                    org.w3c.dom.Element rightsElement = ((org.w3c.dom.Element)rightsSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AccessRights").item(i2));
                                    authorizationRuleInstance.getRights().add(AccessRight.valueOf(rightsElement.getTextContent()));
                                }
                            }
                            
                            NodeList elements10 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "CreatedTime");
                            Element createdTimeElement = elements10.getLength() > 0 ? ((Element)elements10.item(0)) : null;
                            if (createdTimeElement != null)
                            {
                                Calendar createdTimeInstance;
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(simpleDateFormat.parse(createdTimeElement.getTextContent()));
                                createdTimeInstance = calendar;
                                authorizationRuleInstance.setCreatedTime(createdTimeInstance);
                            }
                            
                            NodeList elements11 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "KeyName");
                            Element keyNameElement = elements11.getLength() > 0 ? ((Element)elements11.item(0)) : null;
                            if (keyNameElement != null)
                            {
                                String keyNameInstance;
                                keyNameInstance = keyNameElement.getTextContent();
                                authorizationRuleInstance.setKeyName(keyNameInstance);
                            }
                            
                            NodeList elements12 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ModifiedTime");
                            Element modifiedTimeElement = elements12.getLength() > 0 ? ((Element)elements12.item(0)) : null;
                            if (modifiedTimeElement != null)
                            {
                                Calendar modifiedTimeInstance;
                                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                                Calendar calendar2 = Calendar.getInstance();
                                calendar2.setTime(simpleDateFormat2.parse(modifiedTimeElement.getTextContent()));
                                modifiedTimeInstance = calendar2;
                                authorizationRuleInstance.setModifiedTime(modifiedTimeInstance);
                            }
                            
                            NodeList elements13 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "PrimaryKey");
                            Element primaryKeyElement = elements13.getLength() > 0 ? ((Element)elements13.item(0)) : null;
                            if (primaryKeyElement != null)
                            {
                                String primaryKeyInstance;
                                primaryKeyInstance = primaryKeyElement.getTextContent();
                                authorizationRuleInstance.setPrimaryKey(primaryKeyInstance);
                            }
                            
                            NodeList elements14 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "SecondaryKey");
                            Element secondaryKeyElement = elements14.getLength() > 0 ? ((Element)elements14.item(0)) : null;
                            if (secondaryKeyElement != null)
                            {
                                String secondaryKeyInstance;
                                secondaryKeyInstance = secondaryKeyElement.getTextContent();
                                authorizationRuleInstance.setSecondaryKey(secondaryKeyInstance);
                            }
                        }
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * Lists the notification hubs associated with a namespace.
    *
    * @return The set of connection details for a service bus entity.
    */
    @Override
    public Future<ServiceBusConnectionDetailsResponse> getConnectionDetailsAsync(final String namespaceName, final String notificationHubName)
    {
        return this.getClient().getExecutorService().submit(new Callable<ServiceBusConnectionDetailsResponse>() { 
            @Override
            public ServiceBusConnectionDetailsResponse call() throws Exception
            {
                return getConnectionDetails(namespaceName, notificationHubName);
            }
         });
    }
    
    /**
    * Lists the notification hubs associated with a namespace.
    *
    * @return The set of connection details for a service bus entity.
    */
    @Override
    public ServiceBusConnectionDetailsResponse getConnectionDetails(String namespaceName, String notificationHubName) throws IOException, ServiceException, ParserConfigurationException, SAXException
    {
        // Validate
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/servicebus/namespaces/" + namespaceName + "/NotificationHubs/" + notificationHubName + "/ConnectionDetails";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-08-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        ServiceBusConnectionDetailsResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServiceBusConnectionDetailsResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "feed");
        Element feedElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (feedElement != null)
        {
            if (feedElement != null)
            {
                for (int i1 = 0; i1 < feedElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry").getLength(); i1 = i1 + 1)
                {
                    org.w3c.dom.Element entriesElement = ((org.w3c.dom.Element)feedElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry").item(i1));
                    ServiceBusConnectionDetail entryInstance = new ServiceBusConnectionDetail();
                    result.getConnectionDetails().add(entryInstance);
                    
                    NodeList elements2 = entriesElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "content");
                    Element contentElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
                    if (contentElement != null)
                    {
                        NodeList elements3 = contentElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ConnectionDetail");
                        Element connectionDetailElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
                        if (connectionDetailElement != null)
                        {
                            NodeList elements4 = connectionDetailElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "KeyName");
                            Element keyNameElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                            if (keyNameElement != null)
                            {
                                String keyNameInstance;
                                keyNameInstance = keyNameElement.getTextContent();
                                entryInstance.setKeyName(keyNameInstance);
                            }
                            
                            NodeList elements5 = connectionDetailElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ConnectionString");
                            Element connectionStringElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                            if (connectionStringElement != null)
                            {
                                String connectionStringInstance;
                                connectionStringInstance = connectionStringElement.getTextContent();
                                entryInstance.setConnectionString(connectionStringInstance);
                            }
                            
                            NodeList elements6 = connectionDetailElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationType");
                            Element authorizationTypeElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
                            if (authorizationTypeElement != null)
                            {
                                String authorizationTypeInstance;
                                authorizationTypeInstance = authorizationTypeElement.getTextContent();
                                entryInstance.setAuthorizationType(authorizationTypeInstance);
                            }
                            
                            NodeList elements7 = connectionDetailElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "Rights");
                            Element rightsSequenceElement = elements7.getLength() > 0 ? ((Element)elements7.item(0)) : null;
                            if (rightsSequenceElement != null)
                            {
                                for (int i2 = 0; i2 < rightsSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AccessRights").getLength(); i2 = i2 + 1)
                                {
                                    org.w3c.dom.Element rightsElement = ((org.w3c.dom.Element)rightsSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AccessRights").item(i2));
                                    entryInstance.getRights().add(AccessRight.valueOf(rightsElement.getTextContent()));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * Lists the notification hubs associated with a namespace.
    *
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<ServiceBusNotificationHubsResponse> listAsync(final String namespaceName)
    {
        return this.getClient().getExecutorService().submit(new Callable<ServiceBusNotificationHubsResponse>() { 
            @Override
            public ServiceBusNotificationHubsResponse call() throws Exception
            {
                return list(namespaceName);
            }
         });
    }
    
    /**
    * Lists the notification hubs associated with a namespace.
    *
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public ServiceBusNotificationHubsResponse list(String namespaceName) throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException, URISyntaxException
    {
        // Validate
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/servicebus/namespaces/" + namespaceName + "/NotificationHubs";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-08-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        ServiceBusNotificationHubsResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServiceBusNotificationHubsResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "feed");
        Element feedElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (feedElement != null)
        {
            if (feedElement != null)
            {
                for (int i1 = 0; i1 < feedElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry").getLength(); i1 = i1 + 1)
                {
                    org.w3c.dom.Element entriesElement = ((org.w3c.dom.Element)feedElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry").item(i1));
                    ServiceBusNotificationHub entryInstance = new ServiceBusNotificationHub();
                    result.getNotificationHubs().add(entryInstance);
                    
                    NodeList elements2 = entriesElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "title");
                    Element titleElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
                    if (titleElement != null)
                    {
                        String titleInstance;
                        titleInstance = titleElement.getTextContent();
                        entryInstance.setName(titleInstance);
                    }
                    
                    NodeList elements3 = entriesElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "content");
                    Element contentElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
                    if (contentElement != null)
                    {
                        NodeList elements4 = contentElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "NotificationHubDescription");
                        Element notificationHubDescriptionElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                        if (notificationHubDescriptionElement != null)
                        {
                            NodeList elements5 = notificationHubDescriptionElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "RegistrationTtl");
                            Element registrationTtlElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                            if (registrationTtlElement != null)
                            {
                                String registrationTtlInstance;
                                registrationTtlInstance = registrationTtlElement.getTextContent();
                                entryInstance.setRegistrationTtl(registrationTtlInstance);
                            }
                            
                            NodeList elements6 = notificationHubDescriptionElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationRules");
                            Element authorizationRulesSequenceElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
                            if (authorizationRulesSequenceElement != null)
                            {
                                for (int i2 = 0; i2 < authorizationRulesSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationRule").getLength(); i2 = i2 + 1)
                                {
                                    org.w3c.dom.Element authorizationRulesElement = ((org.w3c.dom.Element)authorizationRulesSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AuthorizationRule").item(i2));
                                    ServiceBusSharedAccessAuthorizationRule authorizationRuleInstance = new ServiceBusSharedAccessAuthorizationRule();
                                    entryInstance.getAuthorizationRules().add(authorizationRuleInstance);
                                    
                                    NodeList elements7 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ClaimType");
                                    Element claimTypeElement = elements7.getLength() > 0 ? ((Element)elements7.item(0)) : null;
                                    if (claimTypeElement != null)
                                    {
                                        String claimTypeInstance;
                                        claimTypeInstance = claimTypeElement.getTextContent();
                                        authorizationRuleInstance.setClaimType(claimTypeInstance);
                                    }
                                    
                                    NodeList elements8 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ClaimValue");
                                    Element claimValueElement = elements8.getLength() > 0 ? ((Element)elements8.item(0)) : null;
                                    if (claimValueElement != null)
                                    {
                                        String claimValueInstance;
                                        claimValueInstance = claimValueElement.getTextContent();
                                        authorizationRuleInstance.setClaimValue(claimValueInstance);
                                    }
                                    
                                    NodeList elements9 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "Rights");
                                    Element rightsSequenceElement = elements9.getLength() > 0 ? ((Element)elements9.item(0)) : null;
                                    if (rightsSequenceElement != null)
                                    {
                                        for (int i3 = 0; i3 < rightsSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AccessRights").getLength(); i3 = i3 + 1)
                                        {
                                            org.w3c.dom.Element rightsElement = ((org.w3c.dom.Element)rightsSequenceElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "AccessRights").item(i3));
                                            authorizationRuleInstance.getRights().add(AccessRight.valueOf(rightsElement.getTextContent()));
                                        }
                                    }
                                    
                                    NodeList elements10 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "CreatedTime");
                                    Element createdTimeElement = elements10.getLength() > 0 ? ((Element)elements10.item(0)) : null;
                                    if (createdTimeElement != null)
                                    {
                                        Calendar createdTimeInstance;
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(simpleDateFormat.parse(createdTimeElement.getTextContent()));
                                        createdTimeInstance = calendar;
                                        authorizationRuleInstance.setCreatedTime(createdTimeInstance);
                                    }
                                    
                                    NodeList elements11 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "KeyName");
                                    Element keyNameElement = elements11.getLength() > 0 ? ((Element)elements11.item(0)) : null;
                                    if (keyNameElement != null)
                                    {
                                        String keyNameInstance;
                                        keyNameInstance = keyNameElement.getTextContent();
                                        authorizationRuleInstance.setKeyName(keyNameInstance);
                                    }
                                    
                                    NodeList elements12 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "ModifiedTime");
                                    Element modifiedTimeElement = elements12.getLength() > 0 ? ((Element)elements12.item(0)) : null;
                                    if (modifiedTimeElement != null)
                                    {
                                        Calendar modifiedTimeInstance;
                                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                                        Calendar calendar2 = Calendar.getInstance();
                                        calendar2.setTime(simpleDateFormat2.parse(modifiedTimeElement.getTextContent()));
                                        modifiedTimeInstance = calendar2;
                                        authorizationRuleInstance.setModifiedTime(modifiedTimeInstance);
                                    }
                                    
                                    NodeList elements13 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "PrimaryKey");
                                    Element primaryKeyElement = elements13.getLength() > 0 ? ((Element)elements13.item(0)) : null;
                                    if (primaryKeyElement != null)
                                    {
                                        String primaryKeyInstance;
                                        primaryKeyInstance = primaryKeyElement.getTextContent();
                                        authorizationRuleInstance.setPrimaryKey(primaryKeyInstance);
                                    }
                                    
                                    NodeList elements14 = authorizationRulesElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "SecondaryKey");
                                    Element secondaryKeyElement = elements14.getLength() > 0 ? ((Element)elements14.item(0)) : null;
                                    if (secondaryKeyElement != null)
                                    {
                                        String secondaryKeyInstance;
                                        secondaryKeyInstance = secondaryKeyElement.getTextContent();
                                        authorizationRuleInstance.setSecondaryKey(secondaryKeyInstance);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
}
