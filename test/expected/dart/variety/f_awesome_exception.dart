// Autogenerated by Frugal Compiler (1.24.2)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

library variety.src.f_awesome_exception;

import 'dart:typed_data' show Uint8List;
import 'package:thrift/thrift.dart';
import 'package:variety/variety.dart' as t_variety;
import 'package:actual_base_dart/actual_base_dart.dart' as t_actual_base_dart;
import 'package:subdir_include_ns/subdir_include_ns.dart' as t_subdir_include_ns;

class AwesomeException extends Error implements TBase {
  static final TStruct _STRUCT_DESC = new TStruct("AwesomeException");
  static final TField _ID_FIELD_DESC = new TField("ID", TType.I64, 1);
  static final TField _REASON_FIELD_DESC = new TField("Reason", TType.STRING, 2);

  /// ID is a unique identifier for an awesome exception.
  int _iD = 0;
  static const int ID = 1;
  /// Reason contains the error message.
  String _reason;
  static const int REASON = 2;

  bool __isset_iD = false;

  AwesomeException() {
  }

  /// ID is a unique identifier for an awesome exception.
  int get iD => this._iD;

  /// ID is a unique identifier for an awesome exception.
  set iD(int iD) {
    this._iD = iD;
    this.__isset_iD = true;
  }

  bool isSetID() => this.__isset_iD;

  unsetID() {
    this.__isset_iD = false;
  }

  /// Reason contains the error message.
  String get reason => this._reason;

  /// Reason contains the error message.
  set reason(String reason) {
    this._reason = reason;
  }

  bool isSetReason() => this.reason != null;

  unsetReason() {
    this.reason = null;
  }

  getFieldValue(int fieldID) {
    switch (fieldID) {
      case ID:
        return this.iD;
      case REASON:
        return this.reason;
      default:
        throw new ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  setFieldValue(int fieldID, Object value) {
    switch(fieldID) {
      case ID:
        if(value == null) {
          unsetID();
        } else {
          this.iD = value;
        }
        break;

      case REASON:
        if(value == null) {
          unsetReason();
        } else {
          this.reason = value;
        }
        break;

      default:
        throw new ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  // Returns true if the field corresponding to fieldID is set (has been assigned a value) and false otherwise
  bool isSet(int fieldID) {
    switch(fieldID) {
      case ID:
        return isSetID();
      case REASON:
        return isSetReason();
      default:
        throw new ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  read(TProtocol iprot) {
    TField field;
    iprot.readStructBegin();
    while(true) {
      field = iprot.readFieldBegin();
      if(field.type == TType.STOP) {
        break;
      }
      switch(field.id) {
        case ID:
          if(field.type == TType.I64) {
            iD = iprot.readI64();
            this.__isset_iD = true;
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case REASON:
          if(field.type == TType.STRING) {
            reason = iprot.readString();
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
          break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  write(TProtocol oprot) {
    validate();

    oprot.writeStructBegin(_STRUCT_DESC);
    oprot.writeFieldBegin(_ID_FIELD_DESC);
    oprot.writeI64(iD);
    oprot.writeFieldEnd();
    if(this.reason != null) {
      oprot.writeFieldBegin(_REASON_FIELD_DESC);
      oprot.writeString(reason);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  String toString() {
    StringBuffer ret = new StringBuffer("AwesomeException(");

    ret.write("iD:");
    ret.write(this.iD);

    ret.write(", ");
    ret.write("reason:");
    if(this.reason == null) {
      ret.write("null");
    } else {
      ret.write(this.reason);
    }

    ret.write(")");

    return ret.toString();
  }

  validate() {
    // check for required fields
    // check that fields of type enum have valid values
  }
}
