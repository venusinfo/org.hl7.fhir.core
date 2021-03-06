package org.hl7.fhir.r5.utils;

/*-
 * #%L
 * org.hl7.fhir.r5
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r5.elementmodel.Element;
import org.hl7.fhir.r5.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.utilities.validation.ValidationMessage;

import com.google.gson.JsonObject;

/**
 * Interface to the instance validator. This takes a resource, in one of many forms, and 
 * checks whether it is valid
   *  
   * @author Grahame Grieve
   *
   */
public interface IResourceValidator {

  public enum ReferenceValidationPolicy {
    IGNORE, CHECK_TYPE_IF_EXISTS, CHECK_EXISTS, CHECK_EXISTS_AND_TYPE, CHECK_VALID;
    
    public boolean checkExists() {
      return this == CHECK_EXISTS_AND_TYPE || this == CHECK_EXISTS || this == CHECK_VALID;
    }
    
    public boolean checkType() {
      return this == CHECK_TYPE_IF_EXISTS || this == CHECK_EXISTS_AND_TYPE || this == CHECK_VALID;
    }
    
    public boolean checkValid() {
      return this == CHECK_VALID;
    }
  }
  
  public interface IValidatorResourceFetcher {
    Element fetch(Object appContext, String url) throws FHIRFormatError, DefinitionException, FHIRException, IOException;
    ReferenceValidationPolicy validationPolicy(Object appContext, String path, String url);
    boolean resolveURL(Object appContext, String path, String url) throws IOException, FHIRException; 
  }
  
  public enum BestPracticeWarningLevel {
    Ignore,
    Hint,
    Warning,
    Error
  }

  public enum CheckDisplayOption {
    Ignore,
    Check,
    CheckCaseAndSpace,
    CheckCase,
    CheckSpace
  }

  enum IdStatus {
    OPTIONAL, REQUIRED, PROHIBITED
  }
  
  

  /**
   * how much to check displays for coded elements 
   * @return
   */
  CheckDisplayOption getCheckDisplay();
  void setCheckDisplay(CheckDisplayOption checkDisplay);

  /**
   * whether the resource must have an id or not (depends on context)
   * 
   * @return
   */

	IdStatus getResourceIdRule();
	void setResourceIdRule(IdStatus resourceIdRule);
  
  /**
   * whether the validator should enforce best practice guidelines
   * as defined by various HL7 committees 
   *  
   */
  BestPracticeWarningLevel getBestPracticeWarningLevel();
  IResourceValidator setBestPracticeWarningLevel(BestPracticeWarningLevel value);

  IValidatorResourceFetcher getFetcher();
  IResourceValidator setFetcher(IValidatorResourceFetcher value);
  
  boolean isNoBindingMsgSuppressed();
  IResourceValidator setNoBindingMsgSuppressed(boolean noBindingMsgSuppressed);
  
  public boolean isNoInvariantChecks();
  public IResourceValidator setNoInvariantChecks(boolean value) ;
  
  public boolean isNoTerminologyChecks();
  public IResourceValidator setNoTerminologyChecks(boolean noTerminologyChecks);

  public boolean isNoExtensibleWarnings();
  public IResourceValidator setNoExtensibleWarnings(boolean noExtensibleWarnings);
  
  /**
   * Whether being unable to resolve a profile in found in Resource.meta.profile or ElementDefinition.type.profile or targetProfile is an error or just a warning
   * @return
   */
  public boolean isErrorForUnknownProfiles();
  public void setErrorForUnknownProfiles(boolean errorForUnknownProfiles);

  /**
   * Validate suite
   *  
   * you can validate one of the following representations of resources:
   *  
   * stream - provide a format - this is the preferred choice
   * 
   * Use one of these two if the content is known to be valid XML/JSON, and already parsed
   * - a DOM element or Document
   * - a Json Object
   *  
   * In order to use these, the content must already be parsed - e.g. it must syntactically valid    
   * - a native resource
   * - a elementmodel resource  
   * 
   * in addition, you can pass one or more profiles ti validate beyond the base standard - as structure definitions or canonical URLs 
   * @throws IOException 
   */
  void validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.elementmodel.Element element) throws FHIRException;
  void validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.elementmodel.Element element, ValidationProfileSet profiles) throws FHIRException;
  void validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.elementmodel.Element element, String profile) throws FHIRException;
  void validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.elementmodel.Element element, StructureDefinition profile) throws FHIRException;
  
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, InputStream stream, FhirFormat format) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, InputStream stream, FhirFormat format, ValidationProfileSet profiles) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, InputStream stream, FhirFormat format, String profile) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, InputStream stream, FhirFormat format, StructureDefinition profile) throws FHIRException;

  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.model.Resource resource) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.model.Resource resource, ValidationProfileSet profiles) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.model.Resource resource, String profile) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.hl7.fhir.r5.model.Resource resource, StructureDefinition profile) throws FHIRException;

  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Element element) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Element element, ValidationProfileSet profiles) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Element element, String profile) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Element element, StructureDefinition profile) throws FHIRException;

  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Document document) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Document document, ValidationProfileSet profiles) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Document document, String profile) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, org.w3c.dom.Document document, StructureDefinition profile) throws FHIRException;

  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, JsonObject object) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, JsonObject object, ValidationProfileSet profiles) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, JsonObject object, String profile) throws FHIRException;
  org.hl7.fhir.r5.elementmodel.Element validate(Object Context, List<ValidationMessage> errors, JsonObject object, StructureDefinition profile) throws FHIRException; 


}
