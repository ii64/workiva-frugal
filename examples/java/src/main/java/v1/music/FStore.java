/**
 * Autogenerated by Frugal Compiler (1.24.2)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */

package v1.music;

import com.workiva.frugal.exception.FMessageSizeException;
import com.workiva.frugal.exception.FRateLimitException;
import com.workiva.frugal.exception.FTimeoutException;
import com.workiva.frugal.middleware.InvocationHandler;
import com.workiva.frugal.middleware.ServiceMiddleware;
import com.workiva.frugal.processor.FBaseProcessor;
import com.workiva.frugal.processor.FProcessor;
import com.workiva.frugal.processor.FProcessorFunction;
import com.workiva.frugal.protocol.*;
import com.workiva.frugal.transport.FTransport;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.transport.TTransport;

import javax.annotation.Generated;
import java.util.concurrent.*;


@Generated(value = "Autogenerated by Frugal Compiler (1.24.2)", date = "2017-1-3")
public class FStore {

	/**
	 * Services are the API for client and server interaction.
	 * Users can buy an album or enter a giveaway for a free album.
	 */
	public interface Iface {

		public Album buyAlbum(FContext ctx, String ASIN, String acct) throws TException, PurchasingError;

		public boolean enterAlbumGiveaway(FContext ctx, String email, String name) throws TException;

	}

	public static class Client implements Iface {

		protected final Object writeLock = new Object();
		private Iface proxy;

		public Client(FTransport transport, FProtocolFactory protocolFactory, ServiceMiddleware... middleware) {
			Iface client = new InternalClient(transport, protocolFactory, writeLock);
			proxy = InvocationHandler.composeMiddleware(client, Iface.class, middleware);
		}

		public Album buyAlbum(FContext ctx, String ASIN, String acct) throws TException, PurchasingError {
			return proxy.buyAlbum(ctx, ASIN, acct);
		}

		public boolean enterAlbumGiveaway(FContext ctx, String email, String name) throws TException {
			return proxy.enterAlbumGiveaway(ctx, email, name);
		}

	}

	private static class InternalClient implements Iface {

		private FTransport transport;
		private FProtocolFactory protocolFactory;
		private FProtocol inputProtocol;
		private FProtocol outputProtocol;
		private final Object writeLock;

		public InternalClient(FTransport transport, FProtocolFactory protocolFactory, Object writeLock) {
			this.transport = transport;
			this.transport.setRegistry(new FClientRegistry());
			this.protocolFactory = protocolFactory;
			this.inputProtocol = this.protocolFactory.getProtocol(this.transport);
			this.outputProtocol = this.protocolFactory.getProtocol(this.transport);
			this.writeLock = writeLock;
		}

		public Album buyAlbum(FContext ctx, String ASIN, String acct) throws TException, PurchasingError {
			FProtocol oprot = this.outputProtocol;
			BlockingQueue<Object> result = new ArrayBlockingQueue<>(1);
			this.transport.register(ctx, recvBuyAlbumHandler(ctx, result));
			try {
				synchronized (writeLock) {
					oprot.writeRequestHeader(ctx);
					oprot.writeMessageBegin(new TMessage("buyAlbum", TMessageType.CALL, 0));
					Store.buyAlbum_args args = new Store.buyAlbum_args();
					args.setASIN(ASIN);
					args.setAcct(acct);
					args.write(oprot);
					oprot.writeMessageEnd();
					oprot.getTransport().flush();
				}

				Object res = null;
				try {
					res = result.poll(ctx.getTimeout(), TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "buyAlbum interrupted: " + e.getMessage());
				}
				if (res == null) {
					throw new FTimeoutException("buyAlbum timed out");
				}
				if (res instanceof TException) {
					throw (TException) res;
				}
				Store.buyAlbum_result r = (Store.buyAlbum_result) res;
				if (r.isSetSuccess()) {
					return r.success;
				}
				if (r.error != null) {
					throw r.error;
				}
				throw new TApplicationException(TApplicationException.MISSING_RESULT, "buyAlbum failed: unknown result");
			} finally {
				this.transport.unregister(ctx);
			}
		}

		private FAsyncCallback recvBuyAlbumHandler(final FContext ctx, final BlockingQueue<Object> result) {
			return new FAsyncCallback() {
				public void onMessage(TTransport tr) throws TException {
					FProtocol iprot = InternalClient.this.protocolFactory.getProtocol(tr);
					try {
						iprot.readResponseHeader(ctx);
						TMessage message = iprot.readMessageBegin();
						if (!message.name.equals("buyAlbum")) {
							throw new TApplicationException(TApplicationException.WRONG_METHOD_NAME, "buyAlbum failed: wrong method name");
						}
						if (message.type == TMessageType.EXCEPTION) {
							TApplicationException e = TApplicationException.read(iprot);
							iprot.readMessageEnd();
							if (e.getType() == FTransport.RESPONSE_TOO_LARGE || e.getType() == FRateLimitException.RATE_LIMIT_EXCEEDED) {
								TException ex;
								if (e.getType() == FTransport.RESPONSE_TOO_LARGE){
									ex = new FMessageSizeException(FTransport.RESPONSE_TOO_LARGE, "response too large for transport");
								}
								else {
									ex = new FRateLimitException(e.getMessage());
								}
								try {
									result.put(ex);
									return;
								} catch (InterruptedException ie) {
									throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "buyAlbum interrupted: " + ie.getMessage());
								}
							}
							try {
								result.put(e);
							} finally {
								throw e;
							}
						}
						if (message.type != TMessageType.REPLY) {
							throw new TApplicationException(TApplicationException.INVALID_MESSAGE_TYPE, "buyAlbum failed: invalid message type");
						}
						Store.buyAlbum_result res = new Store.buyAlbum_result();
						res.read(iprot);
						iprot.readMessageEnd();
						try {
							result.put(res);
						} catch (InterruptedException e) {
							throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "buyAlbum interrupted: " + e.getMessage());
						}
					} catch (TException e) {
						try {
							result.put(e);
						} finally {
							throw e;
						}
					}
				}
			};
		}

		public boolean enterAlbumGiveaway(FContext ctx, String email, String name) throws TException {
			FProtocol oprot = this.outputProtocol;
			BlockingQueue<Object> result = new ArrayBlockingQueue<>(1);
			this.transport.register(ctx, recvEnterAlbumGiveawayHandler(ctx, result));
			try {
				synchronized (writeLock) {
					oprot.writeRequestHeader(ctx);
					oprot.writeMessageBegin(new TMessage("enterAlbumGiveaway", TMessageType.CALL, 0));
					Store.enterAlbumGiveaway_args args = new Store.enterAlbumGiveaway_args();
					args.setEmail(email);
					args.setName(name);
					args.write(oprot);
					oprot.writeMessageEnd();
					oprot.getTransport().flush();
				}

				Object res = null;
				try {
					res = result.poll(ctx.getTimeout(), TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "enterAlbumGiveaway interrupted: " + e.getMessage());
				}
				if (res == null) {
					throw new FTimeoutException("enterAlbumGiveaway timed out");
				}
				if (res instanceof TException) {
					throw (TException) res;
				}
				Store.enterAlbumGiveaway_result r = (Store.enterAlbumGiveaway_result) res;
				if (r.isSetSuccess()) {
					return r.success;
				}
				throw new TApplicationException(TApplicationException.MISSING_RESULT, "enterAlbumGiveaway failed: unknown result");
			} finally {
				this.transport.unregister(ctx);
			}
		}

		private FAsyncCallback recvEnterAlbumGiveawayHandler(final FContext ctx, final BlockingQueue<Object> result) {
			return new FAsyncCallback() {
				public void onMessage(TTransport tr) throws TException {
					FProtocol iprot = InternalClient.this.protocolFactory.getProtocol(tr);
					try {
						iprot.readResponseHeader(ctx);
						TMessage message = iprot.readMessageBegin();
						if (!message.name.equals("enterAlbumGiveaway")) {
							throw new TApplicationException(TApplicationException.WRONG_METHOD_NAME, "enterAlbumGiveaway failed: wrong method name");
						}
						if (message.type == TMessageType.EXCEPTION) {
							TApplicationException e = TApplicationException.read(iprot);
							iprot.readMessageEnd();
							if (e.getType() == FTransport.RESPONSE_TOO_LARGE || e.getType() == FRateLimitException.RATE_LIMIT_EXCEEDED) {
								TException ex;
								if (e.getType() == FTransport.RESPONSE_TOO_LARGE){
									ex = new FMessageSizeException(FTransport.RESPONSE_TOO_LARGE, "response too large for transport");
								}
								else {
									ex = new FRateLimitException(e.getMessage());
								}
								try {
									result.put(ex);
									return;
								} catch (InterruptedException ie) {
									throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "enterAlbumGiveaway interrupted: " + ie.getMessage());
								}
							}
							try {
								result.put(e);
							} finally {
								throw e;
							}
						}
						if (message.type != TMessageType.REPLY) {
							throw new TApplicationException(TApplicationException.INVALID_MESSAGE_TYPE, "enterAlbumGiveaway failed: invalid message type");
						}
						Store.enterAlbumGiveaway_result res = new Store.enterAlbumGiveaway_result();
						res.read(iprot);
						iprot.readMessageEnd();
						try {
							result.put(res);
						} catch (InterruptedException e) {
							throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "enterAlbumGiveaway interrupted: " + e.getMessage());
						}
					} catch (TException e) {
						try {
							result.put(e);
						} finally {
							throw e;
						}
					}
				}
			};
		}

	}

	public static class Processor extends FBaseProcessor implements FProcessor {

		public Processor(Iface iface, ServiceMiddleware... middleware) {
			super(getProcessMap(iface, new java.util.HashMap<String, FProcessorFunction>(), middleware));
		}

		protected Processor(Iface iface, java.util.Map<String, FProcessorFunction> processMap, ServiceMiddleware[] middleware) {
			super(getProcessMap(iface, processMap, middleware));
		}

		private static java.util.Map<String, FProcessorFunction> getProcessMap(Iface handler, java.util.Map<String, FProcessorFunction> processMap, ServiceMiddleware[] middleware) {
			handler = InvocationHandler.composeMiddleware(handler, Iface.class, middleware);
			processMap.put("buyAlbum", new BuyAlbum(handler));
			processMap.put("enterAlbumGiveaway", new EnterAlbumGiveaway(handler));
			return processMap;
		}

		private static class BuyAlbum implements FProcessorFunction {

			private Iface handler;

			public BuyAlbum(Iface handler) {
				this.handler = handler;
			}

			public void process(FContext ctx, FProtocol iprot, FProtocol oprot) throws TException {
				Store.buyAlbum_args args = new Store.buyAlbum_args();
				try {
					args.read(iprot);
				} catch (TException e) {
					iprot.readMessageEnd();
					synchronized (WRITE_LOCK) {
						writeApplicationException(ctx, oprot, TApplicationException.PROTOCOL_ERROR, "buyAlbum", e.getMessage());
					}
					throw e;
				}

				iprot.readMessageEnd();
				Store.buyAlbum_result result = new Store.buyAlbum_result();
				try {
					result.success = this.handler.buyAlbum(ctx, args.ASIN, args.acct);
					result.setSuccessIsSet(true);
				} catch (PurchasingError error) {
					result.error = error;
				} catch (FRateLimitException e) {
					writeApplicationException(ctx, oprot, FRateLimitException.RATE_LIMIT_EXCEEDED, "buyAlbum", e.getMessage());
					return;
				} catch (TException e) {
					synchronized (WRITE_LOCK) {
						writeApplicationException(ctx, oprot, TApplicationException.INTERNAL_ERROR, "buyAlbum", "Internal error processing buyAlbum: " + e.getMessage());
					}
					throw e;
				}
				synchronized (WRITE_LOCK) {
					try {
						oprot.writeResponseHeader(ctx);
						oprot.writeMessageBegin(new TMessage("buyAlbum", TMessageType.REPLY, 0));
						result.write(oprot);
						oprot.writeMessageEnd();
						oprot.getTransport().flush();
					} catch (TException e) {
						if (e instanceof FMessageSizeException) {
							writeApplicationException(ctx, oprot, FTransport.RESPONSE_TOO_LARGE, "buyAlbum", "response too large: " + e.getMessage());
						} else {
							throw e;
						}
					}
				}
			}
		}

		private static class EnterAlbumGiveaway implements FProcessorFunction {

			private Iface handler;

			public EnterAlbumGiveaway(Iface handler) {
				this.handler = handler;
			}

			public void process(FContext ctx, FProtocol iprot, FProtocol oprot) throws TException {
				Store.enterAlbumGiveaway_args args = new Store.enterAlbumGiveaway_args();
				try {
					args.read(iprot);
				} catch (TException e) {
					iprot.readMessageEnd();
					synchronized (WRITE_LOCK) {
						writeApplicationException(ctx, oprot, TApplicationException.PROTOCOL_ERROR, "enterAlbumGiveaway", e.getMessage());
					}
					throw e;
				}

				iprot.readMessageEnd();
				Store.enterAlbumGiveaway_result result = new Store.enterAlbumGiveaway_result();
				try {
					result.success = this.handler.enterAlbumGiveaway(ctx, args.email, args.name);
					result.setSuccessIsSet(true);
				} catch (FRateLimitException e) {
					writeApplicationException(ctx, oprot, FRateLimitException.RATE_LIMIT_EXCEEDED, "enterAlbumGiveaway", e.getMessage());
					return;
				} catch (TException e) {
					synchronized (WRITE_LOCK) {
						writeApplicationException(ctx, oprot, TApplicationException.INTERNAL_ERROR, "enterAlbumGiveaway", "Internal error processing enterAlbumGiveaway: " + e.getMessage());
					}
					throw e;
				}
				synchronized (WRITE_LOCK) {
					try {
						oprot.writeResponseHeader(ctx);
						oprot.writeMessageBegin(new TMessage("enterAlbumGiveaway", TMessageType.REPLY, 0));
						result.write(oprot);
						oprot.writeMessageEnd();
						oprot.getTransport().flush();
					} catch (TException e) {
						if (e instanceof FMessageSizeException) {
							writeApplicationException(ctx, oprot, FTransport.RESPONSE_TOO_LARGE, "enterAlbumGiveaway", "response too large: " + e.getMessage());
						} else {
							throw e;
						}
					}
				}
			}
		}

		private static void writeApplicationException(FContext ctx, FProtocol oprot, int type, String method, String message) throws TException {
			TApplicationException x = new TApplicationException(type, message);
			oprot.writeResponseHeader(ctx);
			oprot.writeMessageBegin(new TMessage(method, TMessageType.EXCEPTION, 0));
			x.write(oprot);
			oprot.writeMessageEnd();
			oprot.getTransport().flush();
		}

	}

}