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

package com.microsoft.windowsazure.management.monitoring.autoscale.models;

/**
* The action for scaling.
*/
public class ScaleAction
{
    private String cooldown;
    
    /**
    * The cooldown interval for scaling action.
    */
    public String getCooldown() { return this.cooldown; }
    
    /**
    * The cooldown interval for scaling action.
    */
    public void setCooldown(String cooldown) { this.cooldown = cooldown; }
    
    private ScaleDirection direction;
    
    /**
    * The direction of scaling.
    */
    public ScaleDirection getDirection() { return this.direction; }
    
    /**
    * The direction of scaling.
    */
    public void setDirection(ScaleDirection direction) { this.direction = direction; }
    
    private ScaleType type;
    
    /**
    * The type of scaling.
    */
    public ScaleType getType() { return this.type; }
    
    /**
    * The type of scaling.
    */
    public void setType(ScaleType type) { this.type = type; }
    
    private String value;
    
    /**
    * The rate of scaling.
    */
    public String getValue() { return this.value; }
    
    /**
    * The rate of scaling.
    */
    public void setValue(String value) { this.value = value; }
    
    /**
    * Initializes a new instance of the ScaleAction class.
    *
    */
    public ScaleAction()
    {
    }
}
