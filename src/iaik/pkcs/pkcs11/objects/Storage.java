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
 * Objects of this class represent PKCS#11 objects of type storage as defined in PKCSC#11 2.11, but
 * is compatible to version 2.01.
 * 
 * @author Karl Scheibelhofer
 * @version 1.0
 * @invariants (token_ != null) and (private_ != null) and (modifiable_ != null) and (label_ !=
 *             null)
 */
public class Storage extends Object {

  /**
   * True, if object is a token object (not a session object).
   */
  protected BooleanAttribute token_;

  /**
   * True, if this is a private object.
   */
  protected BooleanAttribute private_;

  /**
   * True, if this object is modifiable.
   */
  protected BooleanAttribute modifiable_;

  /**
   * The label of this object.
   */
  protected CharArrayAttribute label_;

  /**
   * The default constructor. An application use this constructor to instantiate an object that
   * serves as a template. It may also be useful for working with vendor-defined objects.
   * 
   */
  public Storage() {
    super();
  }

  /**
   * Constructor taking the reference to the PKCS#11 module for accessing the object's attributes,
   * the session handle to use for reading the attribute values and the object handle. This
   * constructor read all attributes that a storage object must contain.
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
  protected Storage(Session session, long objectHandle) throws TokenException {
    super(session, objectHandle);
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
  protected static void putAttributesInTable(Storage object) {
    if (object == null) {
      throw new NullPointerException("Argument \"object\" must not be null.");
    }

    object.attributeTable_.put(Attribute.TOKEN, object.token_);
    object.attributeTable_.put(Attribute.PRIVATE, object.private_);
    object.attributeTable_.put(Attribute.MODIFIABLE, object.modifiable_);
    object.attributeTable_.put(Attribute.LABEL, object.label_);
  }

  /**
   * Allocates the attribute objects for this class and adds them to the attribute table.
   * 
   */
  protected void allocateAttributes() {
    super.allocateAttributes();

    token_ = new BooleanAttribute(Attribute.TOKEN);
    private_ = new BooleanAttribute(Attribute.PRIVATE);
    modifiable_ = new BooleanAttribute(Attribute.MODIFIABLE);
    label_ = new CharArrayAttribute(Attribute.LABEL);

    putAttributesInTable(this);
  }

  /**
   * Create a (deep) clone of this object.
   * 
   * @return A clone of this object.
   * 
   * @postconditions (result != null) and (result instanceof Storage) and (result.equals(this))
   */
  public java.lang.Object clone() {
    Storage clone = (Storage) super.clone();

    clone.token_ = (BooleanAttribute) this.token_.clone();
    clone.private_ = (BooleanAttribute) this.private_.clone();
    clone.modifiable_ = (BooleanAttribute) this.modifiable_.clone();
    clone.label_ = (CharArrayAttribute) this.label_.clone();

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

    if (otherObject instanceof Storage) {
      Storage other = (Storage) otherObject;
      equal = (this == other) || (super.equals(other) && this.token_.equals(other.token_)
          && this.private_.equals(other.private_)
          && this.modifiable_.equals(other.modifiable_)
          && this.label_.equals(other.label_));
    }

    return equal;
  }

  /**
   * Check, if this is a token object.
   * 
   * @return Its value is true, if this is an token object.
   * 
   * @postconditions (result != null)
   */
  public BooleanAttribute getToken() {
    return token_;
  }

  /**
   * Check, if this is a private object.
   * 
   * @return Its value is true, if this is a private object.
   * 
   * @postconditions (result != null)
   */
  public BooleanAttribute getPrivate() {
    return private_;
  }

  /**
   * Check, if this is a modifiable object.
   * 
   * @return Its value is true, if this is a modifiable object.
   * 
   * @postconditions (result != null)
   */
  public BooleanAttribute getModifiable() {
    return modifiable_;
  }

  /**
   * Get the label attribute of this object.
   * 
   * @return Contains the label as a char array.
   * 
   * @postconditions (result != null)
   */
  public CharArrayAttribute getLabel() {
    return label_;
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
    StringBuffer buffer = new StringBuffer(128);

    buffer.append(super.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Token: ");
    buffer.append(token_.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Private: ");
    buffer.append(private_.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Modifiable: ");
    buffer.append(modifiable_.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Label: ");
    buffer.append(label_.toString());

    return buffer.toString();
  }

  /**
   * The overriding of this method should ensure that the objects of this class work correctly in a
   * hashtable.
   * 
   * @return The hash code of this object.
   */
  public int hashCode() {
    return token_.hashCode() ^ private_.hashCode() ^ modifiable_.hashCode()
        ^ label_.hashCode();
  }

}
