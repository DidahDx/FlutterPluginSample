import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'pluign_sample_method_channel.dart';

abstract class PluignSamplePlatform extends PlatformInterface {
  /// Constructs a PluignSamplePlatform.
  PluignSamplePlatform() : super(token: _token);

  static final Object _token = Object();

  static PluignSamplePlatform _instance = MethodChannelPluignSample();

  /// The default instance of [PluignSamplePlatform] to use.
  ///
  /// Defaults to [MethodChannelPluignSample].
  static PluignSamplePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [PluignSamplePlatform] when
  /// they register themselves.
  static set instance(PluignSamplePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> getFaceBookKeyHash(){
    throw UnimplementedError('getFaceBookKeyHash() has not been implemented.');
  }
}
