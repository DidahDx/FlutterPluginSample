import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'pluign_sample_platform_interface.dart';

/// An implementation of [PluignSamplePlatform] that uses method channels.
class MethodChannelPluignSample extends PluignSamplePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('pluign_sample');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<String?> getFaceBookKeyHash() async {
    final String? version = await methodChannel.invokeMethod<String>('getFaceBookKeyHash');
    return version;
  }

}
