package org.hl7.fhir.dstu2.model.valuesets;

/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/

// Generated on Wed, Nov 11, 2015 10:54-0500 for FHIR v1.0.2


public enum ClaimException {

        /**
         * Fulltime Student
         */
        STUDENT, 
        /**
         * Disabled
         */
        DISABLED, 
        /**
         * added to help the parsers
         */
        NULL;
        public static ClaimException fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("student".equals(codeString))
          return STUDENT;
        if ("disabled".equals(codeString))
          return DISABLED;
        throw new Exception("Unknown ClaimException code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case STUDENT: return "student";
            case DISABLED: return "disabled";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/exception";
        }
        public String getDefinition() {
          switch (this) {
            case STUDENT: return "Fulltime Student";
            case DISABLED: return "Disabled";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case STUDENT: return "Student (Fulltime)";
            case DISABLED: return "Disabled";
            default: return "?";
          }
    }


}
