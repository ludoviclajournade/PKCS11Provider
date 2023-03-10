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

import iaik.pkcs.pkcs11.wrapper.CK_ECDH1_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

/**
 * This abstract class encapsulates parameters for the DH mechanisms Mechanism.ECDH1_DERIVE and
 * Mechanism.ECDH1_COFACTOR_DERIVE.
 * 
 * @author Karl Scheibelhofer
 * @version 1.0
 * 
 */
public class EcDH1KeyDerivationParameters extends DHKeyDerivationParameters {

  /**
   * The data shared between the two parties.
   */
  protected byte[] sharedData_;

  /**
   * Create a new EcDH1KeyDerivationParameters object with the given attributes.
   * 
   * @param keyDerivationFunction
   *          The key derivation function used on the shared secret value. One of the values defined
   *          in KeyDerivationFunctionType.
   * @param sharedData
   *          The data shared between the two parties.
   * @param publicData
   *          The other partie's public key value.
   * @preconditions ((keyDerivationFunction == KeyDerivationFunctionType.NULL) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA1_KDF) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA224_KDF) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA256_KDF) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA384_KDF) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA512_KDF) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA1_KDF_ASN1) or
   *                (keyDerivationFunction == KeyDerivationFunctionType.SHA1_KDF_CONCATENATE)) and
   *                (publicData != null)
   * 
   */
  public EcDH1KeyDerivationParameters(long keyDerivationFunction, byte[] sharedData,
      byte[] publicData) {
    super(keyDerivationFunction, publicData);
    sharedData_ = sharedData;
  }

  /**
   * Create a (deep) clone of this object.
   * 
   * @return A clone of this object.
   * 
   * @postconditions (result != null) and (result instanceof EcDH1KeyDerivationParameters) and
   *                 (result.equals(this))
   */
  public java.lang.Object clone() {
    EcDH1KeyDerivationParameters clone = (EcDH1KeyDerivationParameters) super.clone();

    clone.sharedData_ = (byte[]) this.sharedData_.clone();

    return clone;
  }

  /**
   * Get this parameters object as an object of the CK_ECDH1_DERIVE_PARAMS class.
   * 
   * @return This object as a CK_ECDH1_DERIVE_PARAMS object.
   * 
   * @postconditions (result != null)
   */
  public Object getPKCS11ParamsObject() {
    CK_ECDH1_DERIVE_PARAMS params = new CK_ECDH1_DERIVE_PARAMS();

    params.kdf = keyDerivationFunction_;
    params.pSharedData = sharedData_;
    params.pPublicData = publicData_;

    return params;
  }

  /**
   * Get the data shared between the two parties.
   * 
   * @return The data shared between the two parties.
   */
  public byte[] getSharedData() {
    return sharedData_;
  }

  /**
   * Set the data shared between the two parties.
   * 
   * @param sharedData
   *          The data shared between the two parties.
   * @preconditions (sharedData != null)
   * 
   */
  public void setSharedData(byte[] sharedData) {
    sharedData_ = sharedData;
  }

  /**
   * Returns the string representation of this object. Do not parse data from this string, it is for
   * debugging only.
   * 
   * @return A string representation of this object.
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(super.toString());
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("Shared Data: ");
    buffer.append(Functions.toHexString(sharedData_));
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

    if (otherObject instanceof EcDH1KeyDerivationParameters) {
      EcDH1KeyDerivationParameters other = (EcDH1KeyDerivationParameters) otherObject;
      equal = (this == other) || (super.equals(other)
          && Functions.equals(this.sharedData_, other.sharedData_));
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
    return super.hashCode() ^ Functions.hashCode(sharedData_);
  }

}
