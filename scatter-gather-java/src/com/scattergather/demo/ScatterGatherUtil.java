package com.scattergather.demo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * The Class ScatterGatherUtil. Provides raw implementation to execute
 * process/function asynchronously. With functions like
 * {@link #processAsync(List)}, utility initiate a new thread to process a
 * request, waits for completion of all async process and finally returns the
 * data as a list by combining all results.
 *
 * One needs to override {@link #perform(Object)} function to provide a behavior
 * to the functionality to be executed in a scatter-gather mode.
 *
 * <b>Note - This utility needs improvement to handle exception handling and
 * timeout scenarios </b>
 *
 * @param <O> the generic type, Output type object
 * @param <I> the generic type, Input Type Object
 *
 * @author Vipin Gupta
 */
public abstract class ScatterGatherUtil<O, I> {

	/**
	 * Process async function accepts inputs as a list of provided objects to
	 * execute and combine result as a scatter gather function.
	 *
	 * Please note that {@link #perform(Object)} must be overridden before
	 * calling this function.
	 *
	 * @param inputList the input list
	 * @return the lists
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<O> processAsync(final List<I> inputList) throws InterruptedException, ExecutionException{

		final List<CompletableFuture<O>> futures = inputList.stream()
				        .map(inputObj -> performAsCompletableFuture(inputObj))
				        .collect(Collectors.toList());

		final CompletableFuture<Void> allFutures = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture[futures.size()])
		);
		
		final CompletableFuture<List<O>> allOfFutures = allFutures.thenApply(v -> {
			   return futures.stream()
			           .map(future -> future.join())
			           .collect(Collectors.toList());
		});

		return allOfFutures.get();
	}

	/**
	 * Perform as completable future.
	 *
	 * @param inputObject the input object
	 * @return the completable future
	 */
	protected CompletableFuture<O> performAsCompletableFuture(final I inputObject) {
		CompletableFuture<O> result = CompletableFuture.supplyAsync(() -> perform(inputObject));
		result.thenRun(  
				() -> System.out.println("Computation finished for thread : "+Thread.currentThread().getId()));
		return result;
	}

	/**
	 * Perform function executes the behavior provided to it by it's
	 * implementation class.
	 *
	 * @param inputObject the input object
	 * @return the output type
	 */
	public abstract O perform(I inputObject);

}