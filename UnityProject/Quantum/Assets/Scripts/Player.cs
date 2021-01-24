using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Player : MonoBehaviour
{



	// Use this for initialization
	void Start()
	{

	}



	// Update is called once per frame
	void Update()
	{
		if (Input.GetKey(KeyCode.D)){
			if (GetComponent<SpriteRenderer>().flipX==true){
				GetComponent<SpriteRenderer>().flipX=false;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			transform.Translate(0.015f,0,0);
		}
		else if (Input.GetKey(KeyCode.A)){
			if (GetComponent<SpriteRenderer>().flipX==false){
				GetComponent<SpriteRenderer>().flipX=true;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			transform.Translate(-0.015f,0,0);
		}
		else if (Input.GetKey(KeyCode.W)){
			if (GetComponent<SpriteRenderer>().flipY==true){
				GetComponent<SpriteRenderer>().flipY=false;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			transform.Translate(0,0.015f,0);
		}
		else if (Input.GetKey(KeyCode.S)){

			GetComponent<Animator>().SetBool("Walk",true);
			transform.Translate(0,-0.015f,0);
		}

		if (Input.GetKeyUp (KeyCode.A) || Input.GetKeyUp (KeyCode.D) || Input.GetKeyUp (KeyCode.S) || Input.GetKeyUp (KeyCode.W)){
			GetComponent<Animator>().SetBool("Walk",false);
		}


	}



}