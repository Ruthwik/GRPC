// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clientstream.proto

package com.example.calculator;

public final class Clientstream {
  private Clientstream() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_sample_ComputeAverageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_sample_ComputeAverageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_sample_ComputeAverageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_sample_ComputeAverageResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022clientstream.proto\022\ncom.sample\"\'\n\025Comp" +
      "uteAverageRequest\022\016\n\006number\030\001 \001(\005\")\n\026Com" +
      "puteAverageResponse\022\017\n\007average\030\001 \001(\0012p\n\021" +
      "CalculatorService\022[\n\016ComputeAverage\022!.co" +
      "m.sample.ComputeAverageRequest\032\".com.sam" +
      "ple.ComputeAverageResponse\"\000(\001B\032\n\026com.ex" +
      "ample.calculatorP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_sample_ComputeAverageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_sample_ComputeAverageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_sample_ComputeAverageRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_com_sample_ComputeAverageResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_sample_ComputeAverageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_sample_ComputeAverageResponse_descriptor,
        new java.lang.String[] { "Average", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
