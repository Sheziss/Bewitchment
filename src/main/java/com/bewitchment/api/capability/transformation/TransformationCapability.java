package com.bewitchment.api.capability.transformation;

public class TransformationCapability
{
	public enum Transformation
	{
		NONE(true), WEREWOLF(false), VAMPIRE(false), SPECTRE(false), HUNTER(false);
		
		public final boolean canCrossSalt;
		
		private Transformation(boolean canCrossSalt)
		{
			this.canCrossSalt = canCrossSalt;
		}
	}
	
	private Transformation transformation;
	
	public Transformation getTransformation()
	{
		return transformation == null ? Transformation.NONE : transformation;
	}
	
	public void setTransformation(Transformation transformation)
	{
		this.transformation = transformation;
	}
}