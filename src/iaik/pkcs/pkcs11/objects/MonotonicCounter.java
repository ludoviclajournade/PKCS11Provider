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
 * Objects of this class represent a monotonic counter as specified by PKCS#11 v2.11. Remind that
 * this is a snapshot; this means that this object does not get the values from the token on demand
 * it gets them upon instantiation.
 * 
 * @author Karl Scheibelhofer
 * @version 1.0
 * @invariants (resetOnInit_ != null) and (hasReset_ != null) and (value_ != null)
 */
public class MonotonicCounter extends HardwareFeature {

  /**
   * True, if this counter is reset on token initialization.
   */
  protected BooleanAttribute resetOnInit_;

  /**
   * True, if this counter has been reset at least once.
   */
  protected BooleanAttribute hasReset_;

  /**
   * The value attribute of this monotonic counter.
   */
  protected ByteArrayAttribute value_;

  /**
   * Default Constructor.
   * 
   */
  public MonotonicCounter() {
    super();
    hardwareFeatureType_.setLongValue(FeatureType.MONOTONIC_COUNTER);
  }

  /**
   * Called by getInstance to create an instance of a PKCS#11 monotonic counter.
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
  protected MonotonicCounter(Session session, long objectHandle) throws TokenException {
    super(session, objectHandle);
    hardwareFeatureType_.setLongValue(FeatureType.MONOTONIC_COUNTER);
  }

  /**
   * The getInstance method of the HardwareFeature class uses this method to create an instance of a
   * PKCS#11 monotonic counter.
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
    return new MonotonicCounter(session, objectHandle);
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
  protected static void putAttributesInTable(MonotonicCounter object) {
    if (object == null) {
      throw new NullPointerException("Argument \"object\" must not be null.");
    }

    object.attributeTable_.put(Attribute.RESET_ON_INIT, object.resetOnInit_);
    object.attributeTable_.put(Attribute.HAS_RESET, object.hasReset_);
    object.attributeTable_.put(Attribute.VALUE, object.value_);
  }

  /**
   * Allocates the attribute objects for this class and adds them to the attribute table.
   * 
   */
  protected void allocateAttributes() {
    super.allocateAttributes();

    resetOnInit_ = new BooleanAttribute(Attribute.RESET_ON_INIT);
    hasReset_ = new BooleanAttribute(Attribute.HAS_RESET);
    value_ = new ByteArrayAttribute(Attribute.VALUE);

    putAttributesInTable(this);
  }

  /**
   * Create a (deep) clone of this object.
   * 
   * @return A clone of this object.
   * 
   * @postconditions (result != null) and (result instanceof MonotonicCounter) and
   *                 (result.equals(this))
   */
  public java.lang.Object clone() {
    MonotonicCounter clone = (MonotonicCounter) super.clone();

    clone.resetOnInit_ = (BooleanAttribute) this.resetOnInit_.clone();
    clone.hasReset_ = (BooleanAttribute) this.hasReset_.clone();
    clone.value_ = (ByteArrayAttribute) this.value_.clone();

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

    if (otherObject instanceof MonotonicCounter) {
      MonotonicCounter other = (MonotonicCounter) otherObject;
      equal = (this == other) || (super.equals(other)
          && this.resetOnInit_.equals(other.resetOnInit_)
          && this.hasReset_.equals(other.hasReset_) && this.value_.equals(other.value_));
    }

    return equal;
  }

  /**
   * Gets the has-reset attribute of this monotonic counter object.
   * 
   * @return The has-reset attribute.
   * 
   * @postconditions (result != null)
   */
  public BooleanAttribute getHasReset() {
    return hasReset_;
  }

  /**
   * Gets the reset-on-init attribute of this monotonic counter object.
   * 
   * @return The reset-on-init attribute.
   * 
   * @postconditions (result != null)
   */
  public BooleanAttribute isResetOnInit() {
    return resetOnInit_;
  }

  /**
   * Gets the value attribute of this monotonic counter object.
   * 
   * @return The value attribute.
   * 
   * @postconditions (result != null)
   */
  public ByteArrayAttribute getValue() {
    return value_;
  }

  /**
   * The overriding of this method should ensure that the objects of this class work correctly in a
   * hashtable.
   * 
   * @return The hash code of this object.
   */
  public int hashCode() {
    return resetOnInit_.hashCode() ^ hasReset_.hashCode() ^ value_.hashCode();
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
    StringBuffer buffer = new StringBuffer(256);

    buffer.append(super.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Reset on Initialization: ");
    buffer.append(resetOnInit_.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Has been reset: ");
    buffer.append(hasReset_.toString());

    buffer.append(Constants.NEWLINE);
    buffer.append(Constants.INDENT);
    buffer.append("Value (hex): ");
    buffer.append(value_.toString());

    return buffer.toString();
  }

}
