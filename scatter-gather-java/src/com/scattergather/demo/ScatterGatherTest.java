package com.scattergather.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ScatterGatherTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		testMe();
	}
	
	private static void testMe() throws InterruptedException, ExecutionException {
		ScatterGatherUtil<OutputPojo, InputPojo> testMe = new ScatterGatherUtil<OutputPojo, InputPojo>() {
			
			@Override
			public OutputPojo perform(InputPojo inputPojo) {
				try {
					
					System.out.println("new Thread started..." +Thread.currentThread().getId());
					if(inputPojo.getAge()==1 || inputPojo.getAge()==3 || inputPojo.getAge()==6) {
						TimeUnit.SECONDS.sleep(inputPojo.getAge());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return new OutputPojo(inputPojo.getName());
			}
		};
		
		List<InputPojo> inputList = ProvideMeData.prepareInputData();
		
		List<OutputPojo> outputList = testMe.processAsync(inputList);
		
		System.out.println(outputList);
		
	}

	
}

class ProvideMeData {
	 public static List<InputPojo> prepareInputData() {
			final InputPojo mp1 =  new InputPojo("mp1", 1);
			final InputPojo mp2 =  new InputPojo("mp2", 2);
			final InputPojo mp3 =  new InputPojo("mp3", 3);
			final InputPojo mp4 =  new InputPojo("mp4", 4);
			final InputPojo mp5 =  new InputPojo("mp5", 5);
			final InputPojo mp6 =  new InputPojo("mp6", 6);

			List<InputPojo> inputList = new ArrayList<InputPojo>();
			inputList.add(mp1);
			inputList.add(mp2);
			inputList.add(mp3);
			inputList.add(mp4);
			inputList.add(mp5);
			inputList.add(mp6);
			return inputList;
		}
}