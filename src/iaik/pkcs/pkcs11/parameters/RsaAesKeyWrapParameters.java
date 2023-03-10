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


import iaik.pkcs.pkcs11.wrapper.CK_RSA_AES_KEY_WRAP_PARAMS;
import iaik.pkcs.pkcs11.wrapper.CK_RSA_PKCS_OAEP_PARAMS;

/**
 * This class encapsulates parameters for the RSA AES Key Wrapping.
 *
 * @author Patrick Schuster
 * @version 1.0
 */
public class RsaAesKeyWrapParameters implements Parameters {

    protected long ulAESKeyBits;
    protected RSAPkcsOaepParameters pOAEPParams;

    /**
     * Create a new RsaAesKeyWrapParameters object with the given attributes.
     *
     * @param  ulAESKeyBits length of the temporary AES key in bits. Can be only 128, 192 or 256.
     * @param  pOAEPParams parameters of the temporary AES key wrapping. See also the description of <br>
     *                     PKCS #1 RSA OAEP mechanism parameters.
     */
public RsaAesKeyWrapParameters(long ulAESKeyBits, RSAPkcsOaepParameters pOAEPParams) {
        this.ulAESKeyBits = ulAESKeyBits;
        this.pOAEPParams = pOAEPParams;
    }

    /**
     * Create a (deep) clone of this object.
     *
     * @return A clone of this object.
     * @postconditions (result != null) and (result instanceof RsaAesKeyWrapParameters) and
     * (result.equals(this))
     */
    public Object clone() {
        return new RsaAesKeyWrapParameters(ulAESKeyBits, (RSAPkcsOaepParameters) pOAEPParams.clone());
    }

    /**
     * Get this parameters object as an object of the CK_SALSA20_PARAMS class.
     *
     * @return This object as a CK_SALSA20_PARAMS object.
     * @postconditions (result != null)
     */
    public Object getPKCS11ParamsObject() {

        CK_RSA_AES_KEY_WRAP_PARAMS params = new CK_RSA_AES_KEY_WRAP_PARAMS();
        params.ulAESKeyBits = ulAESKeyBits;
        params.pOAEPParams = (CK_RSA_PKCS_OAEP_PARAMS) pOAEPParams.getPKCS11ParamsObject();
        return params;
    }

    /**
     * Returns the string representation of this object. Do not parse data from this string, it is for
     * debugging only.
     *
     * @return A string representation of this object.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        //TODO
        return buffer.toString();
    }

    /**
     * Compares all member variables of this object with the other object. Returns only true, if all
     * are equal in both objects.
     *
     * @param otherObject The other object to compare to.
     * @return True, if other is an instance of this class and all member variables of both objects
     * are equal. False, otherwise.
     */
    public boolean equals(Object otherObject) {
        boolean equal = false;

        if (otherObject instanceof RsaAesKeyWrapParameters) {
            RsaAesKeyWrapParameters other = (RsaAesKeyWrapParameters) otherObject;
            equal = (this == other) || (super.equals(other)
                    && this.pOAEPParams.equals(other.pOAEPParams)
                    && this.ulAESKeyBits == other.ulAESKeyBits);
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
        return (int) (super.hashCode() ^ pOAEPParams.hashCode());
    }

}
