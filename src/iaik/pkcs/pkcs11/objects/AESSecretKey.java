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

package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

/**
 * Objects of this class represent AES secret keys as specified by PKCS#11 v2.11.
 * 
 * @author Karl Scheibelhofer
 * @version 1.0
 * @invariants (value_ != null) and (valueLen_ != null)
 */
public class AESSecretKey extends SecretKey {

  /**
   * The value attribute of this secret key.
   */
  protected ByteArrayAttribute value_;

  /**
   * The length of this secret key in bytes.
   */
  protected LongAttribute valueLen_;

  /**
   * Default Constructor.
   * 
   */
  public AESSecretKey() {
    super();
    keyType_.setLongValue(KeyType.AES);
  }

  /**
   * Called by getInstance to create an instance of a PKCS#11 AES secret key.
   * 
   * @param session
   *          The session to use for reading attributes. This session must have the appropriate
   *          rights; i.e. it must be a user-session, if it is a private object.
   * @param objectHandle
   *          The object handle as given from the PKCS#111 module.
   * @exception TokenException
   *              If getting the attributes failed.
   * @preconditions (session != null)
   * 
   */
  protected AESSecretKey(Session session, long objectHandle) throws TokenException {
    super(session, objectHandle);
    keyType_.setLongValue(KeyType.AES);
  }

  /**
   * The getInstance method of the SecretKey class uses this method to create an instance of a
   * PKCS#11 AES secret key.
   * 
   * @param session
   *          The session to use for reading attributes. This session must have the appropriate
   *          rights; i.e. it must be a user-session, if it is a private object.
   * @param objectHandle
   *          The object handle as given from the PKCS#111 module.
   * @return The object representing the PKCS#11 object. The returned object can be casted to the
   *         according sub-class.
   * @exception TokenException
   *              If getting the attributes failed.
   * @preconditions (session != null)
   * @postconditions (result != null)
   */
  public static Object getInstance(Session session, long objectHandle)
      throws TokenException {
    return new AESSecretKey(session, objectHandle);
  }

  /**
   * Put all attributes of the given object into the attributes table of this object. This method is
   * only static to be able to access invoke the implementation of this method for each class
   * separately (see use in clone()).
   * 
   * @param object
   *          The object to handle.
   * @preconditions (object != null)
   * 
   */
  protected static void putAttributesInTable(AESSecretKey object) {
    if (object == null) {
      throw new NullPointerException("Argument \"object\" must not be null.");
    }

    object.attributeTable_.put(Attribute.VALUE, object.value_);
    object.attributeTable_.put(Attribute.VALUE_LEN, object.valueLen_);
  }

  /**
   * Allocates the attribute objects for this class and adds them to the attribute table.
   * 
   */
  protected void allocateAttributes() {
    super.allocateAttributes();

    value_ = new ByteArrayAttribute(Attribute.VALUE);
    valueLen_ = new LongAttribute(Attribute.VALUE_LEN);

    putAttributesInTable(this);
  }

  /**
   * Create a (deep) clone of this object.
   * 
   * @return A clone of this object.
   * 
   * @postconditions (result != null) and (result instanceof AESSecretKey) and (result.equals(this))
   */
  public java.lang.Object clone() {
    AESSecretKey clone = (AESSecretKey) super.clone();

    clone.value_ = (ByteArrayAttribute) this.value_.clone();
    clone.valueLen_ = (LongAttribute) this.valueLen_.clone();

    putAttributesInTable(clone); // put all cloned attributes into the new table

    return clone;
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

    if (otherObject instanceof AESSecretKey) {
      AESSecretKey other = (AESSecretKey) otherObject;
      equal = (this == other) || (super.equals(other) && this.value_.equals(other.value_)
          && this.valueLen_.equals(other.valueLen_));
    }

    return equal;
  }

  /**
   * Gets the value attribute of this AES key.
   * 
   * @return The value attribute.
   * 
   * @postconditions (result != null)
   */
  public ByteArrayAttribute getValue() {
    return value_;
  }

  /**
   * Gets the value length attribute of this AES key (in bytes).
   * 
   * @return The value attribute.
   * 
   * @postconditions (result != null)
   */
  public LongAttribute getValueLen() {
    return valueLen_;
  }

  /**
   * This method returns a string representation of the current object. The output is only for
   * debugging purposes and should not be used for other purposes.
   * 
   * @return A string presentation of this object for debugging output.
   * 
   * @postconditions (result != null)
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer(1024);

    buffer.append(super.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Value (hex): ");
    buffer.append(value_.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Value Length (dec): ");
    buffer.append(valueLen_.toString(10));

    return buffer.toString();
  }

}
