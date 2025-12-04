import 'package:flutter_test/flutter_test.dart';
import 'package:pluign_sample/pluign_sample.dart';
import 'package:pluign_sample/pluign_sample_platform_interface.dart';
import 'package:pluign_sample/pluign_sample_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockPluignSamplePlatform
    with MockPlatformInterfaceMixin
    implements PluignSamplePlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<String?> getFaceBookKeyHash() {
    return Future.value('asodjfhfd');
  }
}

void main() {
  final PluignSamplePlatform initialPlatform = PluignSamplePlatform.instance;

  test('$MethodChannelPluignSample is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelPluignSample>());
  });

  test('getPlatformVersion', () async {
    PluignSample pluignSamplePlugin = PluignSample();
    MockPluignSamplePlatform fakePlatform = MockPluignSamplePlatform();
    PluignSamplePlatform.instance = fakePlatform;

    expect(await pluignSamplePlugin.getPlatformVersion(), '42');
  });
}
