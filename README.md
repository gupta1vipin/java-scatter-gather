# java-scatter-gather
Plain Java Scatter Gather Utility And Example

# Scatter-Gather is a routing message processor in Mule ESB runtime that sends a request message to multiple targets concurrently. It then collects the responses from all routes and aggregates them back into a single response

# Use class ScatterGatherTest to execute and test. In case of any query, please read www.hybriscx.com or email me on gupta1vipin@gmail.com

The Class ScatterGatherUtil . Provides raw implementation to execute process/function asynchronously. With functions like  {@link #processAsync(List)}, utility initiate a new thread to process a request, waits for completion of all async process and finally returns the data as a list by combining all results.
 
 One needs to override {@link #perform(Object)} function to provide a behavior
 to the functionality to be executed in a scatter-gather mode.

 <b>Note - This utility needs improvement to handle exception handling and timeout scenarios </b>
