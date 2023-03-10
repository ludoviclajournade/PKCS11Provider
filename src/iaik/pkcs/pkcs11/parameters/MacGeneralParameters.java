// Copyright (c) 2002 Graz University of Technology. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without modification,
// are permitted provided that the following conditions are met:
// 
// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
// 
// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
// 
// 3. The end-user documentation included with the redistribution, if any, must
//    include the following acknowledgment:
// 
//    "This product includes software developed by IAIK of Graz University of
//     Technology."
// 
//    Alternately, this acknowledgment may appear in the software itself, if and
//    wherever such third-party acknowledgments normally appear.
// 
// 4. The names "Graz University of Technology" and "IAIK of Graz University of
//    Technology" must not be used to endorse or promote products derived from this
//    software without prior written permission.
// 
// 5. Products derived from this software may not be called "IAIK PKCS Wrapper",
//    nor may "IAIK" appear in their name, without prior written permission of
//    Graz University of Technology.
// 
// THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
// OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
// OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
// ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Constants;

/**
 * This class encapsulates parameters for the MAC algorithms for the following mechanisms: DES, DES3
 * (triple-DES), CAST, CAST3, CAST128 (CAST5), IDEA, and CDMF ciphers.
 * 
 * @author Karl Scheibelhofer
 * @version 1.0
 * 
 */
public class MacGeneralParameters implements Parameters {

  /**
   * The length of the MAC produced, in bytes.
   */
  protected long macLength_;

  /**
   * Create a new MacGeneralParameters object with the given MAC length.
   * 
   * @param macLength
   *          The length of the MAC produced, in bytes.
   */
  public MacGeneralParameters(long macLength) {
    macLength_ = macLength;
  }

  /**
   * Create a (deep) clone of this object.
   * 
   * @return A clone of this object.
   * 
   * @postconditions (result != null) and (result instanceof MacGeneralParameters) and
   *                 (result.equals(this))
   */
  public java.lang.Object clone() {
    MacGeneralParameters clone;

    try {
      clone = (MacGeneralParameters) super.clone();
    } catch (CloneNotSupportedException ex) {
      // this must not happen, because this class is cloneable
      throw new TokenRuntimeException("An unexpected clone exception occurred.", ex);
    }

    return clone;
  }

  /**
   * Get this parameters object as an Long object.
   * 
   * @return This object as a Long object.
   * 
   * @postconditions (result != null)
   */
  public Object getPKCS11ParamsObject() {
    return new Long(macLength_);
  }

  /**
   * Get the length of the MAC produced, in bytes.
   * 
   * @return The length of the MAC produced, in bytes.
   */
  public long getMacLength() {
    return macLength_;
  }

  /**
   * Set the length of the MAC produced, in bytes.
   * 
   * @param macLength
   *          The length of the MAC produced, in bytes.
   */
  public void setMacLength(long macLength) {
    macLength_ = macLength;
  }

  /**
   * Returns the string representation of this object. Do not parse data from this string, it is for
   * debugging only.
   * 
   * @return A string representation of this object.
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(Constants.INDENT);
    buffer.append("Mac Length (dec): ");
    buffer.append(macLength_);
    // buffer.append(Constants.NEWLINE);

    return buffer.toString();
  }

  /**
   * Compares all member variables of this object with the other object. Returns only true, if all
   * are equal in both objects.
   * 
   * @param otherObject
   *          The other object to compare to.
   * @return True, if other is an instance of this class and all member variables of both objects
   *         are equal. False, otherwise.
   */
  public boolean equals(java.lang.Object otherObject) {
    boolean equal = false;

    if (otherObject instanceof MacGeneralParameters) {
      MacGeneralParameters other = (MacGeneralParameters) otherObject;
      equal = (this == other) || (this.macLength_ == other.macLength_);
    }

    return equal;
  }

  /**
   * The overriding of this method should ensure that the objects of this class work correctly in a
   * hashtable.
   * 
   * @return The hash code of this object.
   */
  public int hashCode() {
    return (int) macLength_;
  }

}
