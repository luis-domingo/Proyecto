using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Player : MonoBehaviour
{

	public float speed=3;
	Rigidbody2D rb;
	// Use this for initialization
	private void Start()
	{
		rb= GetComponent<Rigidbody2D>();
	}



	// Update is called once per frame
	void Update()
	{


		if (Input.GetKey(KeyCode.D)){
			if (GetComponent<SpriteRenderer>().flipX==true){
				GetComponent<SpriteRenderer>().flipX=false;
			}
				GetComponent<Animator>().SetBool("Walk",true);
				//transform.Translate(0.015f,0,0);
			rb.velocity=new Vector2(speed,0);

		}
		else if (Input.GetKey(KeyCode.A)){
			if (GetComponent<SpriteRenderer>().flipX==false){
				GetComponent<SpriteRenderer>().flipX=true;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			//transform.Translate(-0.015f,0,0);
			rb.velocity=new Vector2(-speed,0);
		}
		else if (Input.GetKey(KeyCode.W)){
			if (GetComponent<SpriteRenderer>().flipY==true){
				GetComponent<SpriteRenderer>().flipY=false;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			//transform.Translate(0,0.015f,0);
			rb.velocity=new Vector2(0,speed);
		}
		else if (Input.GetKey(KeyCode.S)){

			GetComponent<Animator>().SetBool("Walk",true);
			//transform.Translate(0,-0.015f,0);
			rb.velocity=new Vector2(0,-speed);
		}

		if (Input.GetKeyUp (KeyCode.A) || Input.GetKeyUp (KeyCode.D) || Input.GetKeyUp (KeyCode.S) || Input.GetKeyUp (KeyCode.W)){
			GetComponent<Animator>().SetBool("Walk",false);
			rb.velocity= new Vector2(0,0);
		}


	}



}