package org.apache.dubbo.sample.tri.inter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.sample.tri.BaseTriWrapConsumerTest;
import org.apache.dubbo.sample.tri.TriSampleConstants;
import org.apache.dubbo.sample.tri.service.WrapGreeter;

import org.junit.BeforeClass;

public class TriInterfaceWrapConsumerTest extends BaseTriWrapConsumerTest {

    @BeforeClass
    public static void initStub() {
        ReferenceConfig<WrapGreeter> ref = new ReferenceConfig<>();
        ref.setInterface(WrapGreeter.class);
        ref.setCheck(false);
        ref.setTimeout(3000);
        ref.setUrl(TriSampleConstants.DEFAULT_ADDRESS);
        ref.setProtocol(CommonConstants.TRIPLE);
        ref.setLazy(true);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        ApplicationConfig applicationConfig = new ApplicationConfig(TriInterfaceWrapConsumerTest.class.getName());
        applicationConfig.setMetadataServicePort(TriSampleConstants.CONSUMER_METADATA_SERVICE_PORT);
        bootstrap.application(applicationConfig)
                .registry(new RegistryConfig(TriSampleConstants.ZK_ADDRESS_MODE_INTERFACE))
                .reference(ref)
                .start();
        delegate = ref.get();
    }

}
