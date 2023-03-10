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

package iaik.pkcs.pkcs11.wrapper;

/**
 * class CK_ECMQV_DERIVE_PARAMS provides the parameters to the CKM_ECMQV_DERIVE mechanism.
 * <p>
 * <B>PKCS#11 structure:</B>
 * 
 * <PRE>
 * typedef struct CK_ECMQV_DERIVE_PARAMS {
 *   CK_EC_KDF_TYPE kdf;
 *   CK_ULONG ulSharedDataLen;
 *   CK_BYTE_PTR pSharedData;
 *   CK_ULONG ulPublicDataLen;
 *   CK_BYTE_PTR pPublicData;
 *   CK_ULONG ulPrivateDataLen;
 *   CK_OBJECT_HANDLE hPrivateData;
 *   CK_ULONG ulPublicDataLen2;
 *   CK_BYTE_PTR pPublicData2;
 *   CK_OBJECT_HANDLE publicKey;
 * } CK_ECMQV_DERIVE_PARAMS;
 * </PRE>
 */
public class CK_ECMQV_DERIVE_PARAMS {

  /**
   * key derivation function used on the shared secret value
   * 
   * <PRE>
   * CK_EC_KDF_TYPE kdf;
   * </PRE>
   */
  public long kdf;

  /**
   * some data shared between the two parties
   * 
   * <PRE>
   * CK_BYTE_PTR pSharedData;
   * </PRE>
   */
  public byte[] pSharedData;

  /**
   * pointer to other party??????s first EC public key value
   * 
   * <PRE>
   * CK_ULONG ulPublicDataLen;
   * CK_BYTE_PTR pPublicData;
   * </PRE>
   */
  public byte[] pPublicData;

  /**
   * the length in bytes of the second EC private key
   * 
   * <PRE>
   * CK_ULONG ulPrivateDataLen;
   * </PRE>
   */
  public long ulPrivateDataLen;

  /**
   * key handle for second EC private key value
   * 
   * <PRE>
   * CK_OBJECT_HANDLE hPrivateData;
   * </PRE>
   */
  public long hPrivateData;

  /**
   * pointer to other party??????s second EC public key value
   * 
   * <PRE>
   * CK_ULONG ulPublicDataLen2;
   * CK_BYTE_PTR pPublicData2;
   * </PRE>
   */
  public byte[] pPublicData2;

  /**
   * Handle to the first party??????s ephemeral public key
   * 
   * <PRE>
   * CK_OBJECT_HANDLE publicKey;
   * </PRE>
   */
  public long publicKey;

  /**
   * Returns the string representation of CK_ECMQV_DERIVE_PARAMS.
   * 
   * @return the string representation of CK_ECMQV_DERIVE_PARAMS
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(Constants.INDENT);
    buffer.append("kdf: 0x");
    buffer.append(Functions.toFullHexString(kdf));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pSharedDataLen: ");
    buffer.append(pSharedData.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pSharedData: ");
    buffer.append(Functions.toHexString(pSharedData));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPublicDataLen: ");
    buffer.append(pPublicData.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPublicData: ");
    buffer.append(Functions.toHexString(pPublicData));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPrivateDataLen: ");
    buffer.append(ulPrivateDataLen);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("hPrivateData: ");
    buffer.append(hPrivateData);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPublicDataLen2: ");
    buffer.append(pPublicData2.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPublicDat2a: ");
    buffer.append(Functions.toHexString(pPublicData2));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("publicKey: ");
    buffer.append(publicKey);
    // buffer.append(Constants.NEWLINE);

    return buffer.toString();
  }

}
