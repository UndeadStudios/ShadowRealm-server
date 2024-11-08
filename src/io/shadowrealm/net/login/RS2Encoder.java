package io.shadowrealm.net.login;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.shadowrealm.net.Packet;

import java.util.List;

public class RS2Encoder extends MessageToMessageEncoder<Packet> {
	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
		list.add(packet.getPayload());
	}
}
