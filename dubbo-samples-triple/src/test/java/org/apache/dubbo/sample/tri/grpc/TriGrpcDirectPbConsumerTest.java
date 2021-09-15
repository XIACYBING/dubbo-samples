package org.apache.dubbo.sample.tri.grpc;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.sample.tri.BasePbConsumerTest;
import org.apache.dubbo.sample.tri.PbGreeter;
import org.apache.dubbo.sample.tri.TriSampleConstants;
import org.apache.dubbo.sample.tri.service.PbGreeterManual;

import org.junit.BeforeClass;

public class TriGrpcDirectPbConsumerTest extends BasePbConsumerTest {

    @BeforeClass
    public static void init() {
        ReferenceConfig<PbGreeter> ref = new ReferenceConfig<>();
        ref.setInterface(PbGreeter.class);
        ref.setCheck(false);
        ref.setUrl(TriSampleConstants.DEFAULT_ADDRESS);
        ref.setProtocol(CommonConstants.TRIPLE);
        ref.setLazy(true);
        ref.setTimeout(10000);

        ReferenceConfig<PbGreeterManual> ref2 = new ReferenceConfig<>();
        ref2.setInterface(PbGreeterManual.class);
        ref2.setCheck(false);
        ref2.setUrl(TriSampleConstants.DEFAULT_ADDRESS);
        ref2.setProtocol(CommonConstants.TRIPLE);
        ref2.setLazy(true);
        ref2.setTimeout(10000);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("demo-consumer"))
                .reference(ref)
                .reference(ref2)
                .start();
        delegate = ref.get();
        delegateManual = ref2.get();
    }
}
