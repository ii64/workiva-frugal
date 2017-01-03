/**
 * Autogenerated by Frugal Compiler (1.24.2)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */

package v1.music;

import com.workiva.frugal.middleware.InvocationHandler;
import com.workiva.frugal.middleware.ServiceMiddleware;
import com.workiva.frugal.protocol.*;
import com.workiva.frugal.provider.FScopeProvider;
import com.workiva.frugal.transport.FScopeTransport;
import com.workiva.frugal.transport.FSubscription;
import org.apache.thrift.TException;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.*;

import javax.annotation.Generated;
import java.util.logging.Logger;




/**
 * Scopes are a Frugal extension to the IDL for declaring PubSub
 * semantics. Subscribers to this scope will be notified if they win a contest.
 * Scopes must have a prefix.
 */
@Generated(value = "Autogenerated by Frugal Compiler (1.24.2)", date = "2017-1-3")
public class AlbumWinnersSubscriber {

	private static final String DELIMITER = ".";
	private static final Logger LOGGER = Logger.getLogger(AlbumWinnersSubscriber.class.getName());

	private final FScopeProvider provider;
	private final ServiceMiddleware[] middleware;

	public AlbumWinnersSubscriber(FScopeProvider provider, ServiceMiddleware... middleware) {
		this.provider = provider;
		this.middleware = middleware;
	}

	public interface WinnerHandler {
		void onWinner(FContext ctx, Album req);
	}

	public FSubscription subscribeWinner(final WinnerHandler handler) throws TException {
		final String op = "Winner";
		String prefix = "v1.music.";
		final String topic = String.format("%sAlbumWinners%s%s", prefix, DELIMITER, op);
		final FScopeProvider.Client client = provider.build();
		final FScopeTransport transport = client.getTransport();
		transport.subscribe(topic);

		final WinnerHandler proxiedHandler = InvocationHandler.composeMiddleware(handler, WinnerHandler.class, middleware);
		final FSubscription sub = new FSubscription(topic, transport);
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						FContext ctx = client.getProtocol().readRequestHeader();
						Album received = recvWinner(op, client.getProtocol());
						proxiedHandler.onWinner(ctx, received);
					} catch (TException e) {
						if (e instanceof TTransportException) {
							TTransportException transportException = (TTransportException) e;
							if (transportException.getType() == TTransportException.END_OF_FILE) {
								return;
							}
						}
						LOGGER.warning(String.format("Subscriber error receiving %s, discarding frame: %s", topic, e.getMessage()));
						transport.discardFrame();
					}
				}
			}
		}, "subscription").start();

		return sub;
	}

	private Album recvWinner(String op, FProtocol iprot) throws TException {
		TMessage msg = iprot.readMessageBegin();
		if (!msg.name.equals(op)) {
			TProtocolUtil.skip(iprot, TType.STRUCT);
			iprot.readMessageEnd();
			throw new TApplicationException(TApplicationException.UNKNOWN_METHOD);
		}
		Album req = new Album();
		req.read(iprot);
		iprot.readMessageEnd();
		return req;
	}


}
