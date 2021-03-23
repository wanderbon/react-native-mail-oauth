#import "MailOauth.h"

@implementation MailOauth

    RCTPromiseResolveBlock mailResolveBlock;
    RCTPromiseRejectBlock mailRejectBlock;

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initialize) {
    NSDictionary *info = NSBundle.mainBundle.infoDictionary;
    
    MRMailSDK *mailSDK = [MRMailSDK sharedInstance];
    [mailSDK initializeWithClientID:info[@"MailAppID"]
                        redirectURI:info[@"MailRedirect"]];
    mailSDK.returnScheme = info[@"MailReturnScheme"];
    mailSDK.delegate = self;
}


RCT_EXPORT_METHOD(logIn:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    mailResolveBlock = resolve;
    mailRejectBlock = reject;
    
    [MRMailSDK.sharedInstance authorize];
}


- (void)mrMailSDK:(MRMailSDK *)sdk authorizationDidFinishWithResult:(MRSDKAuthorizationResult *)result {
    mailResolveBlock(@{
        @"authCode": result.authorizationCode,
    });
}

- (void)mrMailSDK:(MRMailSDK *)sdk authorizationDidFailWithError:(NSError *)error {
    mailRejectBlock(@"AuthorizationDidFail", error.localizedDescription, error);
}

@end
