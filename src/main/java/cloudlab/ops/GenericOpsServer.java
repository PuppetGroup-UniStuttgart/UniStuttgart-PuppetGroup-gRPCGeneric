package cloudlab.ops;

import java.util.logging.Logger;

import cloudlab.GenericOpsProto.GenericOpsGrpc;
import io.grpc.ServerBuilder;

/**
 * GenericOpsServer:
 * The service GenericOps is binded to this server's port
 */

public class GenericOpsServer {
	private static final Logger logger = Logger.getLogger(GenericOpsServer.class.getName());

	private int port = 50052;
	private io.grpc.Server server;

	private void start() throws Exception {
		server = ServerBuilder.forPort(port).addService(GenericOpsGrpc.bindService(new GenericOpsImpl())).build().start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				GenericOpsServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	// Await termination on the main thread since the grpc library uses daemon
	// threads.

	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	public static void main(String[] args) throws Exception {
		final GenericOpsServer server = new GenericOpsServer();
		server.start();
		server.blockUntilShutdown();
	}
}
