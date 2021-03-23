#import <React/RCTBridgeModule.h>
#import <MRMailSDK/MRMailSDK.h>

@interface MailOauth : NSObject <RCTBridgeModule, MRMailSDKDelegate>

- (void)initialize;

- (void)logIn:(RCTPromiseResolveBlock)resolve
     rejecter:(RCTPromiseRejectBlock)reject;

@end
