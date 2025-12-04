
import 'pluign_sample_platform_interface.dart';

class PluignSample {
  Future<String?> getPlatformVersion() {
    return PluignSamplePlatform.instance.getPlatformVersion();
  }

  Future<String?> getFacebookHashKey(){
    return PluignSamplePlatform.instance.getFaceBookKeyHash();
  }
}
