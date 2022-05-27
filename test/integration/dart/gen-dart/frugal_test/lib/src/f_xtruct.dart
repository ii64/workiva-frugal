// Autogenerated by Frugal Compiler (3.15.1)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

// ignore_for_file: unused_import
// ignore_for_file: unused_field
import 'dart:typed_data' show Uint8List;

import 'package:collection/collection.dart';
import 'package:thrift/thrift.dart' as thrift;
import 'package:frugal_test/frugal_test.dart' as t_frugal_test;

class Xtruct implements thrift.TBase {
  static final thrift.TStruct _STRUCT_DESC = thrift.TStruct('Xtruct');
  static final thrift.TField _STRING_THING_FIELD_DESC = thrift.TField('string_thing', thrift.TType.STRING, 1);
  static final thrift.TField _BYTE_THING_FIELD_DESC = thrift.TField('byte_thing', thrift.TType.BYTE, 4);
  static final thrift.TField _I32_THING_FIELD_DESC = thrift.TField('i32_thing', thrift.TType.I32, 9);
  static final thrift.TField _I64_THING_FIELD_DESC = thrift.TField('i64_thing', thrift.TType.I64, 11);

  String _string_thing;
  static const int STRING_THING = 1;
  int _byte_thing = 0;
  static const int BYTE_THING = 4;
  int _i32_thing = 0;
  static const int I32_THING = 9;
  int _i64_thing = 0;
  static const int I64_THING = 11;

  bool __isset_byte_thing = false;
  bool __isset_i32_thing = false;
  bool __isset_i64_thing = false;

  String get string_thing => this._string_thing;

  set string_thing(String string_thing) {
    this._string_thing = string_thing;
  }

  bool isSetString_thing() => this.string_thing != null;

  unsetString_thing() {
    this.string_thing = null;
  }

  int get byte_thing => this._byte_thing;

  set byte_thing(int byte_thing) {
    this._byte_thing = byte_thing;
    this.__isset_byte_thing = true;
  }

  bool isSetByte_thing() => this.__isset_byte_thing;

  unsetByte_thing() {
    this.__isset_byte_thing = false;
  }

  int get i32_thing => this._i32_thing;

  set i32_thing(int i32_thing) {
    this._i32_thing = i32_thing;
    this.__isset_i32_thing = true;
  }

  bool isSetI32_thing() => this.__isset_i32_thing;

  unsetI32_thing() {
    this.__isset_i32_thing = false;
  }

  int get i64_thing => this._i64_thing;

  set i64_thing(int i64_thing) {
    this._i64_thing = i64_thing;
    this.__isset_i64_thing = true;
  }

  bool isSetI64_thing() => this.__isset_i64_thing;

  unsetI64_thing() {
    this.__isset_i64_thing = false;
  }

  @override
  getFieldValue(int fieldID) {
    switch (fieldID) {
      case STRING_THING:
        return this.string_thing;
      case BYTE_THING:
        return this.byte_thing;
      case I32_THING:
        return this.i32_thing;
      case I64_THING:
        return this.i64_thing;
      default:
        throw ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  @override
  setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
      case STRING_THING:
        if (value == null) {
          unsetString_thing();
        } else {
          this.string_thing = value as String;
        }
        break;

      case BYTE_THING:
        if (value == null) {
          unsetByte_thing();
        } else {
          this.byte_thing = value as int;
        }
        break;

      case I32_THING:
        if (value == null) {
          unsetI32_thing();
        } else {
          this.i32_thing = value as int;
        }
        break;

      case I64_THING:
        if (value == null) {
          unsetI64_thing();
        } else {
          this.i64_thing = value as int;
        }
        break;

      default:
        throw ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  // Returns true if the field corresponding to fieldID is set (has been assigned a value) and false otherwise
  @override
  bool isSet(int fieldID) {
    switch (fieldID) {
      case STRING_THING:
        return isSetString_thing();
      case BYTE_THING:
        return isSetByte_thing();
      case I32_THING:
        return isSetI32_thing();
      case I64_THING:
        return isSetI64_thing();
      default:
        throw ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  @override
  read(thrift.TProtocol iprot) {
    iprot.readStructBegin();
    for (thrift.TField field = iprot.readFieldBegin();
        field.type != thrift.TType.STOP;
        field = iprot.readFieldBegin()) {
      switch (field.id) {
        case STRING_THING:
          if (field.type == thrift.TType.STRING) {
            this.string_thing = iprot.readString();
          } else {
            thrift.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case BYTE_THING:
          if (field.type == thrift.TType.BYTE) {
            this.byte_thing = iprot.readByte();
            this.__isset_byte_thing = true;
          } else {
            thrift.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case I32_THING:
          if (field.type == thrift.TType.I32) {
            this.i32_thing = iprot.readI32();
            this.__isset_i32_thing = true;
          } else {
            thrift.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case I64_THING:
          if (field.type == thrift.TType.I64) {
            this.i64_thing = iprot.readI64();
            this.__isset_i64_thing = true;
          } else {
            thrift.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          thrift.TProtocolUtil.skip(iprot, field.type);
          break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    validate();
  }

  @override
  write(thrift.TProtocol oprot) {
    validate();

    oprot.writeStructBegin(_STRUCT_DESC);
    if (this.string_thing != null) {
      oprot.writeFieldBegin(_STRING_THING_FIELD_DESC);
      oprot.writeString(this.string_thing);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(_BYTE_THING_FIELD_DESC);
    oprot.writeByte(this.byte_thing);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(_I32_THING_FIELD_DESC);
    oprot.writeI32(this.i32_thing);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(_I64_THING_FIELD_DESC);
    oprot.writeI64(this.i64_thing);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @override
  String toString() {
    StringBuffer ret = StringBuffer('Xtruct(');

    ret.write('string_thing:');
    if (this.string_thing == null) {
      ret.write('null');
    } else {
      ret.write(this.string_thing);
    }

    ret.write(', ');
    ret.write('byte_thing:');
    ret.write(this.byte_thing);

    ret.write(', ');
    ret.write('i32_thing:');
    ret.write(this.i32_thing);

    ret.write(', ');
    ret.write('i64_thing:');
    ret.write(this.i64_thing);

    ret.write(')');

    return ret.toString();
  }

  @override
  bool operator ==(Object o) {
    if (o is Xtruct) {
      return this.string_thing == o.string_thing &&
        this.byte_thing == o.byte_thing &&
        this.i32_thing == o.i32_thing &&
        this.i64_thing == o.i64_thing;
    }
    return false;
  }

  @override
  int get hashCode {
    var value = 17;
    value = (value * 31) ^ this.string_thing.hashCode;
    value = (value * 31) ^ this.byte_thing.hashCode;
    value = (value * 31) ^ this.i32_thing.hashCode;
    value = (value * 31) ^ this.i64_thing.hashCode;
    return value;
  }

  Xtruct clone({
    String string_thing,
    int byte_thing,
    int i32_thing,
    int i64_thing,
  }) {
    return Xtruct()
      ..string_thing = string_thing ?? this.string_thing
      ..byte_thing = byte_thing ?? this.byte_thing
      ..i32_thing = i32_thing ?? this.i32_thing
      ..i64_thing = i64_thing ?? this.i64_thing;
  }

  validate() {
  }
}
